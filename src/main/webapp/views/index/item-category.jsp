<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-6 col-sm-4 col-lg-2">
    <a href="${param.link}" class="cat-block">
        <figure>
            <span>
                <img src="${param.image}" alt="Category image">
            </span>
        </figure>
        <h3 class="cat-block-title">${param.title}</h3>
    </a>
</div>