package edu.ssafy.service;

import java.util.List;

import edu.ssafy.dto.MemberDto;

public interface MemberService {
	public void insert(MemberDto m) throws Exception;
	public void update(MemberDto m) throws Exception;
	public void delete(String id) throws Exception;
	public List<MemberDto> select() throws Exception;
	public MemberDto select(String name) throws Exception;
	public MemberDto selectOne(String id) throws Exception;
}
