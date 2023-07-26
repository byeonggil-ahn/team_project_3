<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!-- LightPick -->
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/lightpick@1.6.2/css/lightpick.min.css">
<link rel="stylesheet" type="text/css" href="${path}/resources/css/reset.css">
<link rel="stylesheet" type="text/css" href="${path}/resources/css/load.css">
<link rel="stylesheet" type="text/css" href="${path}/resources/css/layout.css">
<link rel="stylesheet" type="text/css" href="${path}/resources/css/commons.css">
<link rel="stylesheet" type="text/css" href="${path}/resources/css/test.css">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/lightpick@1.6.2/lightpick.min.js"></script>
<script src="${path}/resources/js/date-picker.js"></script>
<!-- 상품 정보 유효성 검사 -->
<script src="${path}/resources/js/product-register-checker.js"></script>
<style>
    label, .invalid-message {
        padding-left: 20px;
    }
    textarea {
        min-height: 200px;
        resize: none;
        border-radius: 25px;
        outline: none;
        padding-left: 1.3em;
        padding-right: 1.3em;
        border-color: #776BFF;
        font-size: 15px;
        padding-top: 1em;
        padding-bottom: 1em;
        border-width: 2px;
        border-style: solid;
    }
    .category-label {
        padding: 0;
        flex-grow: 1;
        display: flex;
        align-items: center;
        justify-content: center;
    }
    .category-label > label {
        padding-left: 5px;
    }
    .category-title:not(:last-child) {
        margin-right: 60px;
    }
</style>
<script type="text/javascript">
    $(function() {
        $(".back-btn").click(function() {
            var result = confirm("되돌아가면 수정하던 내용이 저장되지 않습니다.\n정말 돌아가시겠습니까?");
            if(!result) return false;
        })
    })
</script>

<div class="container-1000">
    <form action="${pageContext.request.contextPath}/admin/productManage/insertProduct" method="post" enctype="multipart/form-data" class="register-form" autocomplete="off">
        <div class="row pb-20">
            <h1>상품 등록</h1>
        </div>
        <div class="row">
            <!-- <input hidden name="productNo" value=""> -->
            <label>상품명</label>
            <input type="text" class="form-input medium w-100" name="product_name">
            <div class="invalid-message">상품명은 한글, 영어, 숫자, 특수문자를 포함한 1~33자입니다.</div>
        </div>
        <div class="row">
            <label>브랜드</label>
            <input type="text" class="form-input medium w-100" name="product_brand">
            <div class="invalid-message">브랜드명은 한글, 영어, 숫자, 특수문자를 포함한 1~10자입니다.</div>
        </div>
        <div class="row pb-5">
            <div class="w-100 ps-20">카테고리</div>
            <div class="w-100 category-label invalid">
                <div class="category-label flex">
                    <input type="radio" id="tag1" name="tag_no" value=1><label class="category-title" for="tag1">아쿠아
                    마린</label>
                    <input type="radio" id="tag2" name="tag_no" value=2><label class="category-title"
                                                                               for="tag2">아로마틱</label>
                    <input type="radio" id="tag3" name="tag_no" value=3><label class="category-title"
                                                                               for="tag3">앰버</label>
                    <input type="radio" id="tag4" name="tag_no" value=4><label class="category-title"
                                                                               for="tag4">우디</label>
                    <input type="radio" id="tag5" name="tag_no" value=5><label class="category-title"
                                                                               for="tag5">달콤</label>
                    <input type="radio" id="tag6" name="tag_no" value=6><label class="category-title"
                                                                               for="tag6">스모키</label>
                    <input type="radio" id="tag7" name="tag_no" value=7><label class="category-title"
                                                                               for="tag7">과일</label>
                    <input type="radio" id="tag8" name="tag_no" value=8><label class="category-title"
                                                                               for="tag8">그린</label>
                </div>
            </div>
            <div class="invalid-message pt-10">올바른 카테고리를 1개 선택해주세요.</div>
        </div>
        <div class="row">
            <label>가격</label>
            <input type="text" class="form-input medium w-100" name="product_price">
            <div class="invalid-message">0에서 2,100,000,000 이하의 숫자를 입력해주세요.</div>
        </div>
        <div class="row">
            <label>재고</label>
            <input type="text" class="form-input medium w-100" name="product_stock">
            <div class="invalid-message">0에서 2,100,000,000 이하의 숫자를 입력해주세요.</div>
        </div>
        <div class="row">
            <label>배송비</label>
            <input type="text" class="form-input medium w-100" name="product_delivery_price">
            <div class="invalid-message">0에서 2,100,000,000 이하의 숫자를 입력해주세요.</div>
        </div>
        <div class="row">
            <label>누적판매수</label>
            <input type="text" class="form-input medium w-100" name="product_sellcount">
            <div class="invalid-message">0에서 2,100,000,000 이하의 숫자를 입력해주세요.</div>
        </div>
        <div class="row">
            <label>등록일</label>
            <input type="text" class="form-input medium w-100 date-picker" name="product_regdate">
            <div class="invalid-message">등록일은 yyyy-mm-dd 형식으로 입력할 수 있습니다.</div>
        </div>
        <div class="row">
            <label>대표이미지</label>
            <input type="file" class="form-input medium w-100 product_mainimg" name="product_mainimgf" accept=".png, .gif, .jpg">
            <div class="invalid-message">1mb 이하의 이미지만 업로드할 수 있습니다.</div>
        </div>
        <div class="row">
            <label>상세이미지</label>
            <input type="file" class="form-input medium w-100 product_subimg" name="product_subimgf" accept=".png, .gif, .jpg">
            <div class="invalid-message">1mb 이하의 이미지만 업로드할 수 있습니다.</div>
        </div>

        <script>
            document.getElementById('product_mainimg').onchange=function (e) {
                if (this.files && this.files[0]) {
                    let reader = new FileReader();
                    reader.readAsDataURL(this.files[0]);
                    reader.onload = function(e) {
                        $(".product_mainimg").attr("src", e.target.result)
                            .width(70).height(90);
                    } // onload_function
                }
            }

            document.getElementById('product_subimg').onchange=function (e) {
                if (this.files && this.files[0]) {
                    let reader = new FileReader();
                    reader.readAsDataURL(this.files[0]);
                    reader.onload = function(e) {
                        $(".product_subimg").attr("src", e.target.result)
                            .width(70).height(90);
                    } // onload_function
                }
            }
        </script>

        <div class="row">
            <label class="w-100">상품 설명</label>
            <textarea class="w-100" name="product_content"></textarea>
            <div class="invalid-message">상품 설명은 1자 이상, 1,000자 이하로 입력할 수 있습니다.</div>
        </div>
        <div class="row">
            <button type="submit" class="form-btn medium positive w-100 register-btn">등록하기</button>
        </div>
        <div class="row">
            <a class="form-btn medium neutral w-100 back-btn" href="${pageContext.request.contextPath}/admin/productManage/list">돌아가기</a>
        </div>
    </form>
</div>
