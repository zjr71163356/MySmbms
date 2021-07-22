<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/head.jsp"%>

<div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>供应商管理页面 >> 供应商添加页面</span>
        </div>
        <div class="providerAdd" id="providerAdd-box">
           <form id="providerForm" name="providerForm" method="post" action="${pageContext.request.contextPath }/jsp/provider.do">
			<input type="hidden" name="method" value="add">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div class="provider-common" id="pList-1">
                    <label for="proCode">供应商编码：</label>
                    <input type="text" name="paras" id="proCode" value="">
					<!-- 放置提示信息 -->
					<font color="red"></font>
                </div>
                <div class="provider-common" id="pList-2">
                    <label for="proName">供应商名称：</label>
                   <input type="text" name="paras" id="proName" value="">
					<font color="red"></font>
                </div>
                <div class="provider-common" id="pList-3">
                    <label for="proContact">联系人：</label>
                    <input type="text" name="paras" id="proContact" value="">
					<font color="red"></font>

                </div>
                <div class="provider-common" id="pList-4">
                    <label for="proPhone">联系电话：</label>
                    <input type="text" name="paras" id="proPhone" value="">
					<font color="red"></font>
                </div>
                <div class="provider-common" id="pList-5">
                    <label for="proAddress">联系地址：</label>
                    <input type="text" name="paras" id="proAddress" value="">
                </div>
                <div class="provider-common" id="pList-6">
                    <label for="proFax">传真：</label>
                    <input type="text" name="paras" id="proFax" value="">
                </div>
                <div class="provider-common" id="pList-7">
                    <label for="proDesc">描述：</label>
                    <input type="text" name="paras" id="proDesc" value="">
                </div>
                <div class="providerAddBtn provider-common" id="pList-8">
                    <input type="button" name="add" id="add" value="保存">
					<input type="button" id="back" name="back" value="返回" >
                </div>
            </form>
     </div>
</div>
</section>
<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/provideradd.js"></script>
