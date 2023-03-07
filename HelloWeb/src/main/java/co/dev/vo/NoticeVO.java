package co.dev.vo;

import java.util.Date;

import lombok.Data;

@Data //lombok 이용
public class NoticeVO {
	//notice 테이블의 데이터 담아놓기 위한 객체
	private int noticeId; //oracle:notice_id ->noticeId
	private String noticeWriter; //notice_writer
	private String noticeTitle; //notice_title
	private String noticeSubject; //notice_subject
	private Date createDate; //create_date
	private int hitCount; //hit_count
	private String attach; //attach
}
