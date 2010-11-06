<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="actionName"
    description="Tên của hành động" required="true" 
    rtexprvalue="true" type="java.lang.String"%>
<%@ attribute name="count"
    description="Tổng số lượng đối tượng cần hiển thị" required="true" 
    rtexprvalue="true" type="java.lang.Long"%>
<%@ attribute name="currentStart"
    description="Trang hiện tại" required="true" 
    rtexprvalue="true" type="java.lang.Long"%>    
<%@ attribute name="pageSize"
    description="Số đối tượng hiển thị trong 1 trang" required="false" 
    rtexprvalue="true" type="java.lang.Integer"%>
<%@ include file="/includes/common.jsp"%>
<c:if test="${empty pageSize}"><c:set var="pageSize" value="20"></c:set></c:if>
<c:set var="pageCount" value="${u:floor(count%pageSize==0 ? count/pageSize : ((count-(count%pageSize))/pageSize)+1)}"></c:set>
<c:choose>
	<c:when test="${count > pageSize}">
		<span>
			<c:if test="${(currentStart > 0)}">
					<ocw:actionLink name="${actionName}">
						<ocw:param name="start" value="0"/>
						<< Trang đầu				
					</ocw:actionLink>
			</c:if>
		</span>
		<span>
			<c:if test="${(currentStart > 0)}">
					<ocw:actionLink name="${actionName}">
						<ocw:param name="start" value="${currentStart-pageSize}"/>
						< Trang trước				
					</ocw:actionLink>
			</c:if>
		</span>
		<c:choose>
			<c:when test="${pageCount<6}">
				<c:forEach begin="1" end="${pageCount}" var="i">
				    <span>
				   		<c:choose>
				   			<c:when test="${currentStart == ((i-1)*pageSize)}">
							    <b>${i}</b>
							</c:when>
							<c:otherwise>
								<ocw:actionLink name="${actionName}">
									<ocw:param name="start" value="${(i-1)*pageSize}"/>
									${i}				
								</ocw:actionLink>
							</c:otherwise>
						</c:choose>
				    </span>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<c:forEach begin="${((currentStart/pageSize+1-2)>0)?(currentStart/pageSize+1-2):1}" 
							end="${((currentStart/pageSize+1+2)<pageCount)?(currentStart/pageSize+1+2):pageCount}" var="i">
				    <span>
				   		<c:choose>
				   			<c:when test="${currentStart == ((i-1)*pageSize)}">
							    <b>${i}</b>
							</c:when>
							<c:otherwise>
								<ocw:actionLink name="${actionName}">
									<ocw:param name="start" value="${(i-1)*pageSize}"/>
									${i}				
								</ocw:actionLink>
							</c:otherwise>
						</c:choose>
				    </span>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		<span>
			<c:if test="${currentStart < ((pageCount-1)*pageSize)}">
					<ocw:actionLink name="${actionName}">
						<ocw:param name="start" value="${currentStart+pageSize}"/>
						Trang sau >
					</ocw:actionLink>
			</c:if>	
		</span>
		<span>
			<c:if test="${currentStart < ((pageCount-1)*pageSize)}">
					<ocw:actionLink name="${actionName}">
						<ocw:param name="start" value="${((pageCount-1)*pageSize)}"/>
						Trang cuối >>				
					</ocw:actionLink>
			</c:if>	
		</span>
	</c:when>
	<c:otherwise>
	    <div class="empty-notif">
	        1
	    </div>
	</c:otherwise>
</c:choose>

