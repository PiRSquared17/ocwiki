<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/includes/common.jsp" %>

<form>
<table>
  <tr>
    <td>Tên chủ đề gốc</td>
    <td><input type="text" id="root-name" name="root-name" value="${param['root-name']}"></input></td>
  </tr>
  <tr>
    <td>hoặc mã chủ đề gốc</td>
    <td><input type="text" id="root-id" name="root-id" value="${param['root-id']}"></input></td>
  </tr>
</table>
<button type="submit" name="submit" onclick="renderRoot(); return false;">Xem</button>
<span id="topicTree-message" class="error-validating"></span>
</form>

<div id="topicTree-container">&nbsp;</div>

<ocw:setJs templateVar="entryTemplate">
<div>
    <span id="topic\#{resource.id}-expand"><a href="javascript:expand(\#{resource.id})"><code>[+]</code></a></span>
    <span id="topic\#{resource.id}-collapse" style="display: none"><a href="javascript:collapse(\#{resource.id})"><code>[-]</code></a></span>
    <span id="topic\#{resource.id}-leaf" style="display: none"><code>[.]</code></span>
    <ocw:articleLinkTemplate id="#{resource.id}">\#{resource.name}</ocw:articleLinkTemplate>
    (<span title="Số chủ đề con">\#{childrenCount}</span>, 
    <span title="Số bài viết thuộc chủ đề và con cháu của nó">\#{articleCount}</span>)
    <div id="topic\#{resource.id}-children" class="topicTree-subContainer"></div>
</div>
</ocw:setJs>

<script type="text/javascript">
<!--

function render(div, id) {
	div = $(div);
    new Ajax.Request(
        restPath + '/topic_reports/' + id + '/children', 
        {
            method: 'get',
            requestHeaders : {
                Accept : 'application/json'
            },
            evalJSON : true,
            onSuccess : function(transport) {
                var children = transport.responseJSON.result;
                div.innerHTML = '';
                for (i = 0; i < children.length; i++) {
                	div.innerHTML += entryTemplate.evaluate(children[i]);
                	if (children[i].childrenCount <= 0) {
                        $('topic' + children[i].resource.id + '-expand').hide();
                        $('topic' + children[i].resource.id + '-leaf').show();
                	}
                }
            },
            onFailure : function(transport) {
                DefaultTemplate.onFailure(transport);
            }
        }); 
}

function renderRoot() {
    if ($F('root-id') != '') {
        new Ajax.Request(
            restPath + '/topic_reports/' + $F('root-id'), 
            {
                method: 'get',
                requestHeaders : {
                    Accept : 'application/json'
                },
                evalJSON : true,
                onSuccess : function(transport) {
                    var root = transport.responseJSON.result;
                    $('topicTree-container').innerHTML = entryTemplate.evaluate(root);
                    if (root.childrenCount > 0) {
                        expand(root.resource.id);
                    } else {
                    	$('topic' + root.resource.id + '-expand').hide();
                        $('topic' + root.resource.id + '-leaf').show();
                    }
                    $('topicTree-message').innerHTML = '';
                },
                onFailure : function(transport) {
                    var code = transport.responseJSON.code;
                    if (code == 'not found') {
                    	$('topicTree-message').innerHTML = 'Không tìm thấy chủ đề';              
                    } else {
                        DefaultTemplate.onFailure(transport);
                    }
                }
            }); 
    }
}

function expand(id) {
	var elem = $('topic' + id + '-children');
	if (elem.innerHTML == '') {
	    render(elem, id);
	} else {
		elem.show();
	}

    $('topic' + id + '-expand').hide();
    $('topic' + id + '-collapse').show();
}

function collapse(id) {
    var elem = $('topic' + id + '-children');
    elem.hide();
    $('topic' + id + '-expand').show();
    $('topic' + id + '-collapse').hide();
}

Event.observe(window, 'load', function() {
    filterNumericKey('root-id');

    new Autocomplete('root-name', {
        serviceUrl : apiPath + '/topic.search',
        minChars : 2,
        maxHeight : 400,
        width : 300,
        deferRequestBy : 100,
        // callback function:
        onSelect : function(value, data) {
            $('root-id').value = data;
            renderRoot();
        }
    });

    renderRoot();
});

//-->
</script>