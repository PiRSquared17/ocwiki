<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>

<c:set var="resource" value="${empty resource ? action.resource : resource}"></c:set>
<c:set var="test" value="${empty article ? action.article : article}"></c:set>

<h3>Sửa ${test.namespace.name}:<jsp:include page="/includes/article.edit.name.jsp"></jsp:include></h3>

<c:set var="i" value="1"></c:set>

<div>
<c:set var="indexsection" value="0"></c:set>
<input type="hidden" id="id-question-add" name="taq_question" 
				value="${param.taq_question}">
<c:forEach items="${test.sections}" var="section">
	<div id = "section-${indexsection}">
		<div>
		${section.content.text}<br>
		<button type="button" onclick="Add_question(${indexsection})">Thêm</button>
		<input type="text" id="id-question-add-${indexsection}"></input>
		</div>
		<p></p>
		<c:set var="indexquestion" value="0"></c:set>
		<c:forEach items="${section.questions}" var="question">
			<div id="Delete-question-id${i}" class="question-wrapper mouse-out"
	                onmouseover="this.removeClassName('mouse-out'); this.addClassName('mouse-in');" 
		                onmouseout="this.removeClassName('mouse-in'); this.addClassName('mouse-out');">
				<div class="question-number-wrapper">
		             <b><ocw:articleLink resource="${question.baseContainer}">Câu ${i}</ocw:articleLink></b> (${question.mark} điểm):
		        </div>
		        <div class="buttons">
			     	<img alt="" src="${templatePath}/images/wrong.png" onclick="del(${indexsection},${indexquestion},${i})">
			     </div>
		        <div class="question-content-wrapper">${question.content}</div>
				<div>
		             <c:set var="j" value="0" />
		             <div class="answer-list-wrapper">
		             <c:forEach items="${question.answers}" var="answer">
		                <div class="answer-wrapper">
		                     <div class="number-wrapper">
		                        <b>${u:alpha(j)}</b>.
		                     </div>
		                     <div>${answer.content}</div>
		                     <c:set var="j" value="${j+1}" />
		                 </div>
		             </c:forEach>
		             </div>
		         </div>
			</div>
			<c:set var="i" value="${i+1}"></c:set>
			<c:set var="indexquestion" value="${indexquestion+1}"></c:set>
		</c:forEach>
		<div id ="add-section-${indexsection}"></div>
		<c:set var="indexsection" value="${indexsection+1}"></c:set>
		<p>
	</div>
</c:forEach>
<div id="Add_question">
</div>
</div>

<ocw:setJs var="questionTemplate">
	<div id="Delete-question-id\#{lastQuestion}" class="question-wrapper mouse-out"
               onmouseover="this.removeClassName('mouse-out'); this.addClassName('mouse-in');" 
                onmouseout="this.removeClassName('mouse-in'); this.addClassName('mouse-out');">
		<div class="question-number-wrapper">
             <b><ocw:articleLink resource="${question.baseContainer}">Câu \#{lastQuestion}</ocw:articleLink></b> (\#{question.mark} điểm):
        </div>
        <div class="buttons">
	     	<img alt="" src="\#{templatePath}/images/wrong.png" onclick="del(\#{section.id},\#{question.id},\#{lastQuestion})">
	    </div>
        <div class="question-content-wrapper">\#{question.content.text}</div>
		<div>
             <c:set var="j" value="0" />
             <div class="answer-list-wrapper" id="add-\#{lasquestion}">
             	\#{answers}
             </div>
         </div>
	</div>
</ocw:setJs>

<ocw:setJs var="deletedTemplate">
    <div id="question\#{index}-deleted" style="text-align: center;">
        Câu hỏi đã bị xoá.
        <a href="#" onclick="undeleteQuestion(\#{indexsection},\#{indexquestion},\#{index}); return false;">Phục hồi</a>
    </div>
</ocw:setJs>

<ocw:setJs var="answerTemplate">
	<div class="answer-wrapper">
        <div class="number-wrapper">
           <b>\#{oder_ans}</b>.
        </div>
        <div>\#{content}</div>
    </div>
</ocw:setJs>

<script type="text/javascript">
DelTemplate = new Template('${deletedTemplate}');
QuestionTempl = new Template('${questionTemplate}');
AnswerTempl = new Template('${answerTemplate}');

var test=null;
var lastQuestion = ${i};
var st_char='ABCDEFGHIJKLMNOPQRSTUVWXYZ';

//Lấy từ server về
	new Ajax.Request(restPath + '/tests/' + resourceId,{
			method: 'get',
			requestHeaders : {
		       Accept : 'application/json'
	  		},
		    evalJSON : true,
		    onSuccess : function(transport) {
		       test = transport.responseJSON.result;
		       template();
		    },
		    onFailure: function(transport){ 
		    	DefaultTemplate.onFailure(transport); 
			}
	})
	function Show(){
		alert($F('section'));
	}
	//Del question Order
	function del(indexsection,indexquestion,index){
		var data={"indexsection":indexsection,"indexquestion":indexquestion,"index": index}; 
		var element=$('Delete-question-id' + index);
		element.insert({after: DelTemplate.evaluate(data)});
		element.hide();
		test.sections[indexsection].questions[indexquestion].deleted=true;
	}
	//Xoa bo lua chon del
	function undeleteQuestion(indexsection,indexquestion,index){
		var element=$('question'+index+'-deleted');
		var deleted=$('Delete-question-id' + index);
		deleted.show();
		element.remove();
		test.sections[indexsection].questions[indexquestion].deleted=false;
	}
	function search_question(){
		
	}
	
	function Add_question(Nosection){
		var sec_length = test.sections.length;
		var ques_length = test.sections[Nosection].questions.length;
		var id_question_add = 'id-question-add-' + Nosection;
		new Ajax.Request(restPath + '/questions/' + $F('id-question-add'),{
			method:'get',
			requestHeaders : {
		      Accept : 'application/json'
		  },
		  evalJSON : true,
		  onSuccess : function(transport) {
			  question = transport.responseJSON.result;
		      var answer = question.answers;
		      var section = test.sections[Nosection];
		      var add_question='add-section-'+Nosection;
		      var answers='';
		      for(index=0;index<answer.length;index++){
			      var content = answer[index].content.text;
			      var u_index = st_char.charAt(index);
			      var dt = {"content":content,"oder_ans":u_index};
				  answers+=AnswerTempl.evaluate(dt);
			      //$(id_add).insert({after: AnswerTempl.evaluate(dt)});
		      }
		      var data={"question":question,"lastQuestion":lastQuestion,
				      "section":section,"templatePath":templatePath,"answers":answers};
		      $(add_question).insert({before: QuestionTempl.evaluate(data)});
		      lastQuestion++;
		      test.sections[Nosection].questions[ques_length] = question;
		      test.sections[Nosection].questions[ques_length].deleted = false;
		  },
		  onFailure: function(){ 
			  DefaultTemplate.onFailure(transport); 
		});
	}

	// Save Test
	EditAction = Class.create();
	EditAction.preview = function(){
	}
	EditAction.save = function(){
	}

	/*$('id-question-add').focus();

	filterNumericKey('txtQuestionId');
	filterNumericKey('txtQuantity');*/
	function template(){
		for(i = 0;i<test.sections.length;i++){
		    new Autocomplete('id-question-add-' + i, {
		        serviceUrl : apiPath + '/question.search?format=qcount',
		        minChars : 2,
		        maxHeight : 400,
		        width : 300,
		        deferRequestBy : 100,
		        // callback function:
		        onSelect : function(value, data,id) {
					$('id-question-add').value = data;
		        }
		    });
		}
	}
</script>