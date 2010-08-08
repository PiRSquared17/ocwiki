<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<c:if test="${not empty message}"><div class="notification">${message}</div></c:if>

<form action="${scriptPath}">
	<input type="hidden" name="action" value="test.addquestion"></input>
	<input type="hidden" name="taq_test" value="${test.id}"></input>
	
	<c:if test="${u:size(test.sections) != 0 && !(u:size(test.sections) == 1 && (empty test.sections[0].text)) }">
		<div>
		   <label>Phần: 
		   <select id="taq_section" name="taq_section">
		       <c:forEach items="${test.sections}" var="section">
		           <option value="${section.id}" ${section.id==param.taq_section ? 'selected' : ''}>
		                  ${section.order} - ${u:ellipsize(u:stripHTML(section.text), 50)}</option>
		       </c:forEach>
		   </select></label>
		   <c:if test="${empty param.taq_section}">
		      <script>$('taq_section').selectedIndex = $('taq_section').children.length-1</script>
		   </c:if>
		</div>
	</c:if>
	<span class="error-validating">${action.sectionError}</span>
	
    <div>
        <label><input type="radio" ${(empty param.mode || param.mode=='search') ? 'checked' : ''} 
            id="type-id" name="mode" value="search"></input>
        Chọn câu hỏi:</label> 
        <input type="text" id="txtQuestionName" name="taq_content" 
                    value="${param.taq_content}"></input>
            </label>
        <input type="hidden" id="txtQuestionId" name="taq_question" 
                 value="${param.taq_question}"></input>
        <span class="error-validating">${searchErr}</span>
    </div>
    <div>
        <label><input type="radio" ${(empty param.mode || param.mode=='id') ? 'checked' : ''} 
            id="type-id" name="mode" value="id"></input>
        Nhập ID câu hỏi:</label> 
        <input type="text" id="txtQuestionId" name="taq_question_id" 
                 value="${param.taq_question_id}"></input>
        <span class="error-validating">${idErr}</span>
    </div>
	<div>
		<label><input type="radio" id="type-random" name="mode" 
			"${param.mode=='random' ? 'checked' : ''} value="random"></input>
		Chọn ngẫu nhiên:</label>
		

		<p><label>Chủ đề:
            <input type="text" id="txtTopicName" name="taq_topicname" 
                    value="${param.taq_topicname}"
                    onfocus="$('type-random').checked = 'checked'"></input>
			</label>
			<input type="hidden" id="txtTopicId" name="taq_topicid" 
			         value="${param.taq_topicid}"></input>
			<span style="color:red">${topicErr}</span>
		</p>

		<p><label>Số lượng: 
			<input type="text" id="txtQuantity" name="taq_quantity" 
				value="${(empty param.taq_quantity) ? 1 : param.taq_quantity}" 
                onfocus="$('type-random').checked = 'checked'"
                maxlength="3">
			</input></label>
			<span class="error-validating">${action.quantityError}</span>
		</p>
		
		<p>
		  <label>
			  <input type="checkbox" name="more" value="true"
			          ${param.more ? 'checked' : ''}></input>
              Thêm nữa			          
		  </label>
		</p>
	</div>

    <div>
		<button type="submit" name="taq_submit" value="add">Lưu</button>
		<button type="button" onclick="location.href='${scriptPath}?action=test.view&tv_id=${test.id}'">Quay về đề thi</button>
	</div>
	
</form>

<script>
	$('txtQuestionId').focus();

	filterNumericKey('txtQuestionId');
	filterNumericKey('txtQuantity');

    new Autocomplete('txtTopicName', {
        serviceUrl : '${scriptPath}?action=topic.ajaxsearch&format=qcount',
        minChars : 2,
        maxHeight : 400,
        width : 300,
        deferRequestBy : 100,
        // callback function:
        onSelect : function(value, data) {
            $('txtTopicId').value = data;
        }
    });

    new Autocomplete('txtQuestionName', {
        serviceUrl : '${scriptPath}?action=question.ajaxsearch&format=qcount',
        minChars : 2,
        maxHeight : 400,
        width : 300,
        deferRequestBy : 100,
        // callback function:
        onSelect : function(value, data) {
            $('txtQuestionId').value = data;
        }
    });
</script>