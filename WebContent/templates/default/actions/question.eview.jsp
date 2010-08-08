<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<div class="article-wrapper question-wrapper">

	<c:set var="question" value="${action.question}"></c:set>
	
	<div class="header">
	
        <div id="tool-menu">
	        <ul class="level1 horizontal" id="tool-menu-root">
	           <li class="level1">
		        <span class="mode-link" id="mode-view-link" style="display: none">
		            [<a href="#" onclick="viewMode(); return false;">xem</a>]
		        </span>
		        <span class="mode-link-disabled" id="mode-view-link-enabled">[xem]</span> 
		       </li>
		       <li class="level1">
		        <span class="mode-link" id="mode-exercise-link"> 
		            [<a href="#" onclick="exerciseMode(); return false;">làm</a>]
		        </span>
		        <span class="mode-link-disabled" id="mode-exercise-link-enabled" style="display: none">[làm]</span>
		       </li>
	           <li class="level1">
	               [<a href="#" onclick="return false;">công cụ</a>]
	               <ul class="level2 dropdown">
	                   <li class="level2"><a href="#bai-giai">bài giải</a></li>
	                   <li class="level2"><a href="#thao-luan">thảo luận</a></li>
	                   <li class="level2"><a href="#lien-quan">liên quan</a></li>
	               </ul>
	           </li>
	        </ul>
        </div>
	
	    <h1 class="name">
	        Câu hỏi:${question.id}
	    </h1>
	
	</div>
	
	<div class="meta-wrapper">
	    Tác giả: <tags:user user="${question.author}" />
	    (${u:formatDateTime(question.createDate)})
	    <c:if test="${not empty question.lastChange}">
	        -
	        Sửa lần cuối bởi <tags:user user="${question.lastChange.user}" />
	        <a href="${ocw:actionUrl('article.changelog')}?article=${question.id}">Nhật kí</a>
	    </c:if> 
	</div>
	
	<div class="mode-container" id="mode-view-container">
		<div class="question-wrapper">
		    <div class="question-content-wrapper" 
					onmouseover="this.addClassName('hover')"
					onmouseout="this.removeClassName('hover')">
	            <div class="button-wrapper">
	               [<a href="#">sửa</a>]
	            </div>
		        ${question.content}
			</div>
			<div class="answer-list-wrapper">
			<c:set var="i" value="0"></c:set>
			<c:forEach items="${question.answers}" var="answer">
			    <c:set var="wrapperId" value="wrapper${answer.id}"></c:set>
				<div class="answer-wrapper" id="${wrapperId}"
					onmouseover="this.addClassName('hover')"
					onmouseout="this.removeClassName('hover')">
				  <div class="number-wrapper">
				      <span class="number">${u:alpha(i)}</span>.
				  </div>
				  <div class="button-wrapper">
				     [<a href="#" onclick="editAnswer(${answer.id}); return false;">sửa</a>]
				  </div>
				  <div class="content-wrapper">
		                ${answer.content}
				  </div>
				  &nbsp;
				</div>
				<c:set var="i" value="${i+1}"></c:set>
			</c:forEach>
			</div>
		</div>
	</div>
	
	<div class="mode-container" style="display: none" id="mode-exercise-container">
		<c:choose>
		    <c:when test="${sessionScope.login}">
				<div class="question-wrapper">
					<div class="question-content-wrapper">
					    ${question.content}
					</div>
					<c:set var="i" value="0" />
					<div class="answer-list-wrapper">
					<c:forEach items="${question.answers}" var="answer">
					    <div class="answer-wrapper" 
					            onmouseover="this.addClassName('hover')"
					            onmouseout="this.removeClassName('hover')">
					        <c:set var="checkId" value="answer-${answer.id}"></c:set>
					        <div class="check-wrapper">
					            <input type="checkbox" name="${question.id}-answers" value="${answer.id}" id="${checkId}"></input>
					        </div>
					        <div class="marker-wrapper">
					            <span id="a${answer.id}-rightanswer" style="display: none;">
					                <img src="${templatePath}/images/right.png" width="16px" height="16px" />
					            </span>
					            <span id="a${answer.id}-wronganswer" style="display: none;">
					                <img src="${templatePath}/images/wrong.png" width="16px" height="16px" />
					            </span>
					        </div>
					        <div class="number-wrapper">
					            <label for="${checkId}">
					               <span class="number">${u:alpha(i)}</span>.
					            </label>
					        </div>
					        <div class="content-wrapper">
					            <label for="${checkId}">${answer.content}</label>
					        </div>
					        &nbsp;
					    </div>
					    <c:set var="i" value="${i+1}" />
					</c:forEach>
				    </div>
				    <button type="button">Trả lời</button>
				</div>
			</c:when>
			<c:otherwise>
			    <div class="message">
			        Hãy <a href="${ocw:actionUrl('user.login')}">đăng nhập</a> để trả lời câu hỏi.
			    </div>
			</c:otherwise>
	   </c:choose>
	</div>
	
	<tags:topics article="${question}" editable="true"/>
	
	<h2 class="section-title"><a name="bai-giai">Bài giải</a></h2>
	<h2 class="section-title"><a name="thao-luan">Thảo luận</a></h2>
	<h2 class="section-title"><a name="lien-quan">Bài liên quan</a></h2>

</div>

<form class="ajax-editor" id="question-editor">
    <input type="hidden" name="id"></input>
    <textarea rows="10" name="content"></textarea>
    <button type="button">Lưu</button>
    <button type="button">Huỷ</button>
</form>

<form class="ajax-editor" id="answer-editor">
    <input type="hidden" name="id"></input>
    <input type="checkbox" name="correct"></input>
    <textarea rows="10" name="content"></textarea>
    <button type="button">Lưu</button>
    <button type="button">Huỷ</button>
</form>
