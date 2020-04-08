# Controller, DAO, VO, mybatis로 REST API 구현하기

- ```java
  package com.Article.Controller;
  
  import com.Article.DAO.ArticleDAO;
  import com.Article.VO.ArticleVO;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.context.ConfigurableApplicationContext;
  import org.springframework.context.support.ClassPathXmlApplicationContext;
  import org.springframework.integration.support.MessageBuilder;
  import org.springframework.messaging.Message;
  import org.springframework.messaging.MessageChannel;
  import org.springframework.stereotype.Controller;
  import org.springframework.web.bind.annotation.*;
  
  import java.io.File;
  import java.io.FileOutputStream;
  import java.util.HashMap;
  import java.util.List;
  import java.util.Map;
  import org.apache.commons.codec.binary.Base64;
  import org.springframework.web.multipart.MultipartFile;
  import org.springframework.web.servlet.mvc.support.RedirectAttributes;
  
  @Controller
  public class ArticleController {
  
      @Autowired
      ArticleDAO articleDAO;
  
      //조회(게시판 타입별)
      //전체
      @RequestMapping(value = "/read/all", method = RequestMethod.GET)
      @ResponseBody
      public List<ArticleVO> article_all(){
          List<ArticleVO> list;
          list=articleDAO.select_list(0);
          return list;
      }
  
  
  
      //게시글 하나 조회
      @RequestMapping(value = "/read/one",method = RequestMethod.GET)
      @ResponseBody
      public ArticleVO article_one(@RequestParam("article_id")int article_id){
          ArticleVO articleVO;
          articleVO=articleDAO.select_one(article_id);
          articleVO=articleDAO.select_comment(articleVO);
          articleDAO.hit(article_id);
          return articleVO;
      }
  
      //작성
      @RequestMapping(value = "/article/write",method = RequestMethod.PUT)
      @ResponseBody
      public Map<String,Object> write(@RequestParam("social_id") String social_id,
                                      @RequestParam("article_type") int article_type,
                                      @RequestParam("nickname") String nickname,
                                      @RequestParam("subject") String subject,
                                      @RequestParam("content") String content,
                                      @RequestParam("article_picture")String article_picture){
          Map<String, Object> map = new HashMap<String, Object>();
  
          ArticleVO articleVO=new ArticleVO();
          articleVO.setArticleVO(social_id,subject, content, article_type, nickname,article_picture);
          try {
              articleDAO.insert_article(articleVO);
              map.put("result","complete");
          } catch (Exception e) {
              map.put("result","fail");
              e.printStackTrace();
          }
          return map;
      }
  
  
      //수정(해당 게시글을 수정하기)
      @RequestMapping(value = "/modify/complete",method = RequestMethod.PUT)
      @ResponseBody
      public Map<String,Object> modify_complete(@RequestParam("article_id") int article_id,@RequestParam("subject") String subject,
                                             @RequestParam("content") String content, @RequestParam("article_type") int article_type,
                                             @RequestParam("nickname") String nickname, @RequestParam("social_id") String social_id,
                                             @RequestParam("article_picture")String article_picture){
          Map<String, Object> map = new HashMap<String, Object>();
          ArticleVO articleVO=new ArticleVO();
          articleVO.setArticleVO(social_id,subject, content, article_type, nickname,article_picture);
          try {
              articleDAO.update_article(articleVO);
              map.put("result","success");
              throw new Exception();
          } catch (Exception e) {
              map.put("result","fail");
              e.printStackTrace();
          }
          return map;
      }
  
      //삭제
      @RequestMapping(value = "/article/delete",method = RequestMethod.DELETE)
      @ResponseBody
      public Map<String,Object> delete(@RequestParam("article_id") int article_id, @RequestParam("social_id")String social_id){
          Map<String, Object> map = new HashMap<String, Object>();
          ArticleVO articleVO=new ArticleVO();
          try {
              articleVO=articleDAO.select_one(article_id);
          } catch (Exception e) {
              e.printStackTrace();
          }
          if(!articleVO.getSocial_id().equals(social_id)){//본인의 게시물이 아닌경우
              map.put("result","fail");
          }
          else {
              try {
                  articleDAO.delete_article(articleVO);
                  map.put("result","success");
              } catch (Exception e) {
                  map.put("result","fail");
                  e.printStackTrace();
              }
          }
  
          return map;
      }
  
  
  ```

- ```java
  package com.Article.DAO;
  
  import com.Article.VO.ArticleVO;
  import org.mybatis.spring.SqlSessionTemplate;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.stereotype.Repository;
  
  import java.util.HashMap;
  import java.util.List;
  import java.util.Map;
  
  @Repository
  public class ArticleDAO {
  
      @Autowired
      SqlSessionTemplate sqlSession;
  
      String namespace="com.Article";
  
      public List<ArticleVO> select_list(int article_type){
          List<ArticleVO> list=null;
          if(article_type==0)//전체 글
              list=sqlSession.selectList(namespace+".select_all");
          if(article_type!=0){//게시판별
              list=sqlSession.selectList(namespace+".select_board",article_type);
          }
          return list;
      }
  
      public ArticleVO select_one(int article_id){
          ArticleVO articleVO=new ArticleVO();
          articleVO=sqlSession.selectOne(namespace+".select_one",article_id);
  
          return articleVO;
      }
  
  
      public void delete_article(ArticleVO articleVO){ sqlSession.delete(namespace+".delete_article",articleVO.getArticle_id()); }
  
      public void update_article(ArticleVO articleVO){sqlSession.update(namespace+".update_article",articleVO);}
  
  
      public void hit(int article_id){sqlSession.update(namespace+".hit",article_id);}
  
  }
  
  ```

- ```java
  package com.Article.VO;
  
  import com.Comment.VO.CommentVO;
  
  import java.sql.Timestamp;
  import java.text.SimpleDateFormat;
  import java.util.ArrayList;
  import java.util.Calendar;
  import java.util.List;
  
  public class ArticleVO {
      //업데이트 시간(현재시간)
      SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
      Calendar cal = Calendar.getInstance();
      String today = formatter.format(cal.getTime());
  
      String social_id;
      int article_id;
      int article_type;
      String subject;
      String content;
      int recommend=0;
      int non_recommend=0;
      int hit=0;
      String article_picture;
      String nickname;
      Timestamp create_time;
      Timestamp update_time=Timestamp.valueOf(today);
      List<CommentVO> commentlist=new ArrayList<CommentVO>() {
  
      };
      int comment_size;
  
      public void setArticleVO(String social_id,String subject,String content,int article_type,String nickname,String article_picture){
          this.subject=subject;
          this.content=content;
          this.article_type=article_type;
          this.nickname=nickname;
          this.social_id=social_id;
          this.article_picture=article_picture;
      }
      public String getSocial_id() {
          return social_id;
      }
  
      public void setSocial_id(String social_id) {
          this.social_id = social_id;
      }
      public String getArticle_picture() {
          return article_picture;
      }
  
      public void setArticle_picture(String article_picture) {
          this.article_picture = article_picture;
      }
  
      public int getArticle_id() {
          return article_id;
      }
  
      public void setArticle_id(int article_id) {
          this.article_id = article_id;
      }
  
      public int getArticle_type() {
          return article_type;
      }
  
      public void setArticle_type(int article_type) {
          this.article_type = article_type;
      }
  
      public String getSubject() {
          return subject;
      }
  
      public void setSubject(String subject) {
          this.subject = subject;
      }
  
      public String getContent() {
          return content;
      }
  
      public void setContent(String content) {
          this.content = content;
      }
  
      public int getRecommend() {
          return recommend;
      }
  
      public void setRecommend(int recommend) {
          this.recommend = recommend;
      }
  
      public int getNon_recommend() {
          return non_recommend;
      }
  
      public void setNon_recommend(int non_recommend) {
          this.non_recommend = non_recommend;
      }
  
      public int getHit() {
          return hit;
      }
  
      public void setHit(int hit) {
          this.hit = hit;
      }
  
      public String getNickname() {
          return nickname;
      }
  
      public void setNickname(String nickname) {
          this.nickname = nickname;
      }
  
      public Timestamp getCreate_time() {
          return create_time;
      }
  
      public void setCreate_time(Timestamp create_time) {
          this.create_time = create_time;
      }
  
      public Timestamp getUpdate_time() {
          return update_time;
      }
  
      public void setUpdate_time(Timestamp update_time) {
          this.update_time = update_time;
      }
  
      public List<CommentVO> getCommentlist() {
          return commentlist;
      }
  
      public void setCommentlist(List<CommentVO> commentlist) {
          this.commentlist=commentlist;
      }
  
      public int getComment_size() {
          return comment_size;
      }
  
  }
  
  ```

- ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <!DOCTYPE mapper
          PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  
  <mapper namespace="com.Article">
      <select id="select_all" resultType="ArticleVO">
      SELECT * FROM happydaram2.article
      </select>
  
      <select id="select_board" resultType="ArticleVO" parameterType="int">
              SELECT * FROM happydaram2.article WHERE article_type=#{article_type}
      </select>
  
      <select id="select_one" resultType="ArticleVO" parameterType="int">
          SELECT * FROM happydaram2.article WHERE article_id=#{article_id}
      </select>
  
      <select id="select_comment" resultType="CommentVO" parameterType="int">
          SELECT * FROM happydaram2.comment WHERE article_id=#{article_id}
      </select>
  
      <select id="search_article" resultType="ArticleVO" parameterType="String">
          SELECT * FROM happydaram2.article WHERE subject LIKE #{keyword};
      </select>
  
      <update id="recommend_article" parameterType="int">
          UPDATE happydaram2.article SET recommend = recommend+1 WHERE (`article_id` = #{article_id});
      </update>
  
      <update id="non_recommend_article" parameterType="int">
          UPDATE happydaram2.article SET non_recommend = non_recommend+1 WHERE (`article_id` = #{article_id});
      </update>
  
      <update id="update_article" parameterType="ArticleVO">
          UPDATE `happydaram2`.`article` SET `subject` = #{subject}, `content` = #{content}, `article_type` = #{article_type},`article_picture` = #{article_picture}
          WHERE article_id=#{article_id};
  
      </update>
  
      <update id="hit" parameterType="int">
                  UPDATE happydaram2.article SET hit = hit+1 WHERE (`article_id` = #{article_id});
      </update>
  
      <insert id="insert_article" parameterType="ArticleVO">
          INSERT INTO `happydaram2`.`article` (`subject`, `content`, `recommend`, `non_recommend`, `article_type`, `hit`, `nickname`,`social_id`,`comment_size`,`article_picture`)
          VALUES (#{subject}, #{content},'0', '0', #{article_type}, '0', #{nickname},#{social_id},'0',#{article_picture});
      </insert>
  
      <delete id="delete_article"  parameterType="int">
          DELETE FROM `happydaram2`.`article` WHERE article_id=#{article_id};
      </delete>
  </mapper>
  
  ```

- 