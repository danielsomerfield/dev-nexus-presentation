(function(){
    function addGoodLink(linkText) {
        $("<a>", {href:"#", text:linkText }).appendTo("#good-links");
    }

    function addEvilLink(linkText) {
        $("#evil-links").append("<a href='#' />${text}</a>".replace("${text}", linkText));
    }

    $(document).ready(function(){
        $("#add-link-form").submit(function(){
            var linkText = $("#link-text").val();
            addGoodLink(linkText);
            addEvilLink(linkText);
            return false;
        });
    });
})();