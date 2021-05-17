package test.study;

import test.dao.MemoDao;
import test.dto.MemoDto;

public class StudyMain {
	public static void main(String[] args) {
		MemoDao memo = MemoDao.getInstance();
		MemoDto memoDto = new MemoDto();
//		String memo1 = "----------";
//		memo.write(memo1);
		memoDto.setNum(6);
		memoDto.setContent("수정되라얍!!");
		memo.update(memoDto);
	}
}
