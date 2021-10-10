function ajaxBrowseItemsPage(page) {
    $.ajax({
        url: "/api/clothing/html",
        type: "get", //send it through get method
        data: {
            page: 1
        },
        success: function(response) {
            $("#itemSection").html(response);
            const $pageLinks = $("#pagination li a");
            $pageLinks.parent().removeClass("active");
            $pageLinks.eq(page + 1).parent().addClass("active");
        },
        error: function(xhr) {
            console.log("bad")
        }
    });
}

$(function () {
    const $pageLinks = $("#pagination li a");
    $pageLinks.eq(getCurrentPage() + 1).parent().addClass("active");
    $pageLinks.each(function (index, el) {
        $(el).click(function () {
            const currentPage = getCurrentPage();
            let browsePage;
            if (index > 0 && index < $pageLinks.length - 1) {
                browsePage = parseInt($(el).text()) - 1;
            } else if(index === 0) {
                browsePage = currentPage > 0 ? currentPage - 1 : 0;
            } else if(index === $("#pagination li a").length - 1) {
                browsePage = currentPage < $pageLinks.length - 3 ? currentPage + 1 : $pageLinks.length - 3;
            }
            //ajaxBrowseItemsPage(browsePage);
            document.location.href = `/admin/wardrobe?page=${browsePage}`;
            return false;
        });
    });
})

function getCurrentPage() {
    const pageParam = new URLSearchParams(window.location.search).get("page");
    return  pageParam ? parseInt(pageParam) : 0;
}