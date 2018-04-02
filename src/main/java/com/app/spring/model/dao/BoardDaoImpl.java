package com.app.spring.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.spring.model.dto.BoardVO;

@Repository
public class BoardDaoImpl implements BoardDAO {
	
	@Autowired // @Inject
	SqlSession sqlSession;
	
	@Override
	public void create(BoardVO vo) throws Exception {
		sqlSession.insert("board.insert", vo);

	}

	@Override
	public BoardVO read(int bno) throws Exception {
		return sqlSession.selectOne("board.view", bno);
	}

	@Override
	public void update(BoardVO vo) throws Exception {
		sqlSession.update("board.updateArticle", vo);

	}

	@Override
	public void delete(int bno) throws Exception {
		sqlSession.delete("board.deleteArticle", bno);

	}

	@Override
	public List<BoardVO> listAll(int start, int end, String searchOption, String keyword) throws Exception {
		//검색옵션과 키워드를 맵에 저장해서 같이 매개변수로!
		Map<String,String> map = new HashMap<String,String>();
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		//BETWEEN #{start} . #{end} 에 입력될 값을 맵에
		map.put("start", start);
		map.put("end", end);
		return sqlSession.selectList("board.listAll",map);
	}

	@Override
	public void increaseViewcnt(int bno) throws Exception {
		sqlSession.update("board.increaseViewcnt", bno);

	}
	
	public int countArticle(String searchOption, String keyword) throws	Exception{
		Map<String,String> map = new HashMap<String,String>();
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		return sqlSession.selectOne("board.countArticle", map);
	}

}
