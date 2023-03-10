<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>






<!-- product/productList.jsp -->
<!-- Section -->

<section class="py-5">
	<div class="container px-4 px-lg-5 mt-5">
		<div
			class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
			<c:forEach var="prod" items="${product }">
				<div class="col mb-5">
					<div class="card h-100">
						<!-- Sale badge-->
						<div class="badge bg-dark text-white position-absolute"
							style="top: 0.5rem; right: 0.5rem">Sale</div>
						<!-- Product image-->
						<img class="card-img-top" src="./images/${prod.image }" alt="..." />
						<!-- Product details-->
						<div class="card-body p-4">
							<div class="text-center">
								<!-- Product name-->
								<h5 class="fw-bolder">${prod.productName }</h5>
								<!-- Product reviews-->
								<div
									class="d-flex justify-content-center small text-warning mb-2">
									<c:forEach begin="1" end="${prod.likeIt }" step="1">
										<div class="bi-star-fill"></div>
									</c:forEach>
									<fmt:formatNumber value="${prod.originPrice }" var="originValue"></fmt:formatNumber>
									<fmt:formatNumber value="${prod.salePrice }" var="calValue"></fmt:formatNumber>
									<c:if test="${originValue != calValue }">
										<div class="bi-star-half"></div>
									</c:if>
									<c:forEach begin="1" end="${5-prod.likeIt }">
										<div class="bi-star"></div>
									</c:forEach>

								</div>
								<!-- Product price-->
								<span class="text-muted text-decoration-line-through">${prod.originPrice }</span>
								${prod.salePrice }
							</div>
						</div>
						<!-- Product actions-->
						<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
							<div class="text-center">
								<a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a>
							</div>
						</div>
					</div>
				</div>

			</c:forEach>

		</div>
	</div>
</section>