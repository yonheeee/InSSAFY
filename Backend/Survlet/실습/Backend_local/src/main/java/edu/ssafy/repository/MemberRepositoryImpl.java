package edu.ssafy.repository;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import edu.ssafy.dto.MemberDto;

public class MemberRepositoryImpl implements MemberRepository {
	static MemberRepositoryImpl repository = new MemberRepositoryImpl();
	private MemberRepositoryImpl() {
		list.add(new MemberDto("id","pw","둘리","11",new String[] {"축구","농구"}));
		list.add(new MemberDto("id","pw","또치","12",new String[] {"알고","자바"}));
		list.add(new MemberDto("id","pw","도우너","13",new String[] {"뷰","음악"}));
	
	};
	
	public static MemberRepository getInstance() {
		return repository;
	}

	private List<MemberDto> list = new ArrayList<>();

	@Override
	public void insert(MemberDto m) throws Exception {
		list.add(m);
		
	}

	@Override
	public void update(MemberDto m) throws Exception {
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getId().equals(m.getId())) {
				list.set(i, m);
			}
		}
	}

	@Override
	public void delete(String id) throws Exception {
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getId().equals(id)) {
				list.remove(i);
				i--;
			}
		}
		
	}

	@Override
	public List<MemberDto> select() throws Exception {
		return list;
	}

	@Override
	public MemberDto select(String name) throws Exception {
		for(MemberDto m : list) {
			if(m.getName().equals(name)) {
				return m;
			}
		}
		return null;
	}

	@Override
	public MemberDto selectOne(String id) throws Exception {
		for(MemberDto m : list) {
			if(m.getId().equals(id)) {
				return m;
			}
		}
		return null;
	}

	@Override
	public MemberDto login(String id, String password) throws Exception {
		for(MemberDto mem:list) {
			if(mem.getId().equals(id) && mem.getPassword().equals(password)) {
				return new MemberDto(id, null, null, null, null);
			}
		}
		return null;
	}

	@Override
	public void save() throws Exception {
		ObjectOutputStream oos= new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("data.ser")));
		oos.writeObject(list);
		oos.close();
	}

	@Override
	public void load() throws Exception {
		ObjectOutputStream ois= new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("data.ser")));
		List<MemberDto> list = (List<MemberDto>)((Object) ois).readObject();
		this.list = list;
		ois.close();
		
	}


}
