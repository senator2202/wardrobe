$(".checkbox-menu").on("change", "input[type='checkbox']", function() {
    $(this).closest("li").toggleClass("active", this.checked);
});
$(document).on('click', '.allow-focus', function (e) {
    e.stopPropagation();
});

async function uploadFile(fileupload) {
    let formData = new FormData();
    console.log(fileupload.files[0])
    formData.append("file", fileupload.files[0]);
    let response = await fetch('/files', {
        method: "POST",
        body: formData
    });

    if (response.status === 200) {
        const content = await response.text();
        const $imageSources = $("#imageSources");
        let newValue = $imageSources.val().length === 0 ? content : $imageSources.val() + ", " + content;
        $imageSources.val(newValue);
    }
}

function showFile(e) {
    var output = document.getElementById('output');
    output.src = URL.createObjectURL(e.target.files[0]);
    output.onload = function() {
        URL.revokeObjectURL(output.src) // free memory
    }
};

function addItemModal() {
    clearModal();
    uploadImage();
    const $modal = $("#addModal");
    $modal.find(".modal-title").text("Add item");
    $modal.modal();
}

function clearModal() {
    const $modal = $("#addModal");
    const $form = $modal.closest("form");
    $form.attr("action", "/admin/wardrobe");
    $form.find("input[type=hidden][name=_method]").remove();
    $modal.find("#imageSources").val("");
    $modal.find("#itemName").val("");
    $modal.find("#itemType").val("");
    $modal.find("#itemPrice").val("");
    $modal.find("#itemColor").val("");
    $modal.find("ul.dropdown-menu.checkbox-menu input[type=checkbox]").prop("checked", false);
    $modal.find(".images").html($modal.find(".pic"));
}