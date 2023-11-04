

$(document).ready(function () {
    owlCarousels();

    function owlCarousels($wrap, options) {
        if ( $.fn.owlCarousel ) {
            var owlSettings = {
                items: 1,
                loop: true,
                margin: 0,
                responsiveClass: true,
                nav: true,
                navText: ['<i class="fas fa-chevron-left"></i>', '<i class="fas fa-chevron-right"></i>'],
                dots: true,
                smartSpeed: 400,
                autoplay: false,
                autoplayTimeout: 5000,
                responsive: {
                    1200: {
                        nav: true,
                        dots: false
                    }
                }
            };
            if (typeof $wrap == 'undefined') {
                $wrap = $('body');
            }
            if (options) {
                owlSettings = $.extend({}, owlSettings, options);
            }
    
            // Init all carousel
            $wrap.find('[data-toggle="owl"]').each(function () {
                var $this = $(this),
                    newOwlSettings = $.extend({}, owlSettings, $this.data('owl-options'));
    
                $this.owlCarousel(newOwlSettings);
                
            });   
        }
    }

    $('.product').hover(
        function() {
          $(this).find('.product-action-vertical').css({
            visibility: 'visible',
            opacity: 1
          });
          $(this).find('.product-action').css({
            visibility: 'visible',
            opacity: 1
          });
        },
        function() {
          $(this).find('.product-action-vertical').css({
            visibility: 'hidden',
            opacity: 0
          });
          $(this).find('.product-action').css({
            visibility: 'hidden',
            opacity: 0
          });
        }
    );
});
