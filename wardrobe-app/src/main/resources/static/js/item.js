function toggleInfo(element) {
    const $productInfo = $(element).closest(".product").find(".product__info");
    const $product = $(element).closest(".product");
    const $expandBtn = $(element);
    const animationTime = 50;
    if ($productInfo.is(":visible")) {
        if($product.attr("replaced") === true) {
            $product.removeAttr("replaced").insertAfter($product.next());
        }
        $productInfo.hide(animationTime);
        $product.animate({"min-width": "300px"}, animationTime, "swing", function () {
            $product.css("grid-template-columns", "initial");
            $expandBtn.text("arrow_forward_ios");
            $expandBtn.focus();
        })
    } else {
        if($product.next().length > 0 && $product.next().offset().top !== $product.offset().top) {
            $product.attr("replaced", true).insertBefore($product.prev());
        }
        $product.css("max-height", $product.height());
        $productInfo.show(animationTime);
        $product.animate({"min-width": "600px"}, animationTime, "swing", function () {
            $product.css("grid-template-columns", "0.9fr 1fr");
            $expandBtn.text("arrow_back_ios");
            $expandBtn.focus();
        })
    }
}

function replaceImage(image) {
    $(image).closest(".product").find(".photo-main img").attr("src", $(image).attr("src"));
}

function deleteItem(itemId) {
    $.ajax({
        url: "/api/clothing",
        type: "DELETE", //send it through get method
        data: {
            id: itemId
        },
        success: function(response) {
            $(`#itemSection${itemId}`).remove();
        },
        error: function(xhr) {
            alert("error")
        }
    });
}

function editItemModal(editButton) {
    const $modal = $("#addModal");
    $modal.find(".modal-title").text("Edit item");
    $modal.find("#itemName").val($(editButton).data("name"));
    $modal.find("#itemType").val($(editButton).data("type"));
    $modal.find("#itemPrice").val($(editButton).data("price"));
    $modal.find("#itemColor").val($(editButton).data("color").toUpperCase());
    let sizes = typeof $(editButton).data("sizes") === "string" ? arrayFromString($(editButton).data("sizes")) : $(editButton).data("sizes");
    $("ul.dropdown-menu.checkbox-menu input[type=checkbox]").prop("checked", false);
    sizes.forEach(el => {
        $modal.find(`ul.dropdown-menu.checkbox-menu input[type=checkbox][name=${el}]`).prop("checked", true);
    });
    $modal.find(".images").html($modal.find(".pic"));
    let imageSources = arrayFromString($(editButton).data("imagesources"));
    $("#imageSources").val(imageSources.join(", "));
    imageSources.reverse().forEach((el, index) => {
        $modal.find(".images").prepend(`<div class='img' style='background-image: url("${el}")'><span>remove</span></div>`)
    });
    uploadImage();
    const itemId = $(editButton).attr("id").replace("editBtn", "");
    const $form = $modal.closest("form");
    $form.attr("action", $form.attr("action") + "/" + itemId);
    $form.prepend("<input type='hidden' name='_method' value='put'>")
    $modal.modal();
}

function arrayFromString(arrayInString) {
    return arrayInString.slice(1, arrayInString.length-1).split(", ")
}