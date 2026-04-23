package edu.ssafy.service;

import java.util.List;

import edu.ssafy.dto.MemberDto;
import edu.ssafy.repository.MemberRepository;
import edu.ssafy.repository.MemberRepositoryImpl;

public class MemberServiceImpl implements MemberService{
	static MemberService service = new MemberServiceImpl();
	
	public static MemberService getInstance() {
		return service;
	}
	
	private MemberRepository repo;
	
	private MemberServiceImpl() {	
		repo = MemberRepositoryImpl.getInstance();
	}

	@Override
	public void insert(MemberDto m) throws Exception {
		repo.insert(m);
		
	}

	@Override
	public void update(MemberDto m) throws Exception {
		repo.update(m);
		
	}

	@Override
	public void delete(String id) throws Exception {
		repo.delete(id);
		
	}

	@Override
	public List<MemberDto> select() throws Exception {
		// TODO Auto-generated method stub
		return repo.select();
	}

	@Override
	public MemberDto select(String name) throws Exception {
		// TODO Auto-generated method stub
		return repo.select(name);
	}

	@Override
	public MemberDto selectOne(String id) throws Exception {
		// TODO Auto-generated method stub
		return repo.selectOne(id);
	}

	@Override
	public boolean login(String id, String password) throws Exception {
		if(repo.login(id, password) != null) {
			return true;
		}
		return false;
	}

	@Override
	public void deleteIds(String[] ids) throws Exception {
		for(String id:ids) {
			repo.delete(id);
		}
		
	}

}
