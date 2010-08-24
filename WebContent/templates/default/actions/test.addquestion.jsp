<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<c:if test="${not empty message}"><div class="notification">${message}</div></c:if>

<ocw:form action="test.addquestion">
	<input type="hidden" name="taq_test" value="${test.id}">
    <input type="hidden" name="basever" value="${action.resource.version}">
	
	<c:if test="${u:size(test.sections) != 0 && !(u:size(test.sections) == 1 && (empty test.sections[0].text)) }">
		<div>
		   <label>Phần: 
		   <select id="taq_section" name="taq_section">
		       <c:forEach items="${test.sections}" var="section">
		           <option value="${section.id}" ${section.id==param.taq_section ? 'selected' : ''}>
		                  ${section.order} - ${u:ellipsize(u:stripHTML(section.content.text), 50)}</option>
		       </c:forEach>
		   </select></label>
		   <c:if test="${empty param.taq_section}">
		      <script>$('taq_section').selectedIndex = $('taq_section').children.length-1</script>
		   </c:if>
		</div>
	</c:if>
	<ocw:error code="section"></ocw:error>
	
    <div>
        <label><input type="radio" ${(empty param.mode || param.mode=='search') ? 'checked' : ''} 
            id="type-id" name="mode" value="search">
        Chọn câu hỏi:</label> 
        <input type="text" id="txtQuestionName" name="taq_content" 
                    value="${param.taq_content}">
        <input type="hidden" id="txtQuestionId" name="taq_question" 
                 value="${param.taq_question}">
        <ocw:error code="search"></ocw:error>
    </div>
    <div>
        <label><input type="radio" ${(empty param.mode || param.mode=='id') ? 'checked' : ''} 
            id="type-id" name="mode" value="id">
        Nhập ID câu hỏi:</label> 
        <input type="text" id="txtQuestionId" name="taq_question_id" 
                 value="${param.taq_question_id}">
        <ocw:error code="id"></ocw:error>
    </div>
	<div>
		<label><input type="radio" id="type-random" name="mode" 
			"${param.mode=='random' ? 'checked' : ''} value="random">
		Chọn ngẫu nhiên:</label>
		

		<p><label>Chủ đề:
            <input type="text" id="txtTopicName" name="taq_topicname" 
                    value="${param.taq_topicname}"
                    onfocus="$('type-random').checked = 'checked'">
			</label>
			<input type="hidden" id="txtTopicId" name="taq_topicid" 
			         value="${param.taq_topicid}">
			<ocw:error code="topic"></ocw:error>
		</p>

		<p><label>Số lượng: 
			<input type="text" id="txtQuantity" name="taq_quantity" 
				value="${(empty param.taq_quantity) ? 1 : param.taq_quantity}" 
                onfocus="$('type-random').checked = 'checked'"
                maxlength="3">
			</label>
			<ocw:error code="quantity"></ocw:error>
		</p>
		
		<p>
		  <label>
			  <input type="checkbox" name="more" value="true"
			          ${param.more ? 'checked' : ''}>
              Thêm nữa			          
		  </label>
		</p>
	</div>

    <div>
		<button type="submit" name="taq_submit" value="add">Lưu</button>
		<ocw:articleButton resource="${action.resource}">Quay về đề thi</ocw:articleButton>
	</div>
</ocw:form>

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