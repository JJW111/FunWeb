package com.funweb.web.command.center.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.funweb.web.dao.BoardDao;
import com.funweb.web.daoimpl.NoticeDaoImpl;
import com.funweb.web.dto.Notice;
import com.funweb.web.icommand.ICommand;
import com.funweb.web.model.BoardModel;
import com.funweb.web.util.LoginManager;
import com.funweb.web.util.RequestUtils;

import jdbccontext.exception.NoUpdateDataException;

public class NoticeWriteProcCommand implements ICommand {
 
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 폼에서 입력된 데이터를 DB에 저장한다.
		Notice dto = new Notice();
		dto.setIdx(LoginManager.getIdx(request));
		dto.setTitle(request.getParameter("title"));
		dto.setContent(request.getParameter("content"));
		dto.setWriter(LoginManager.getUserID(request));
		
		
		
		/* 작성한 글을 DB에 저장한다. */
		BoardDao<Notice> dao = new NoticeDaoImpl();
		BoardModel<Notice> model = new BoardModel<>(request, dao);

		try {
			model.writeProcBoard(dto);
		} catch (NoUpdateDataException e) { // 글이 정상적으로 저장되지 못했을 때
			RequestUtils.setMessage(request, "글이 정상적으로 저장되지 못하였습니다. 다시 시도하여 주십시오.");
			return "/notice.do";
		}
		
		return "/notice.do";
		
	}

}
