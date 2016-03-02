//======================抽奖滚动js实现的两种方式==========================
/**
 * 第一种：当滚动完以后再下一轮滚动
 * <div class="lt_move" id="lt_move">
                    <ul class="lt_list">
                        <li>
                            <span class="lt_mobile">131*****523</span><span>2016-03-01</span>
                        </li>
                        <li>
                            <span class="lt_mobile">131*****523</span><span>2016-03-01</span>
                        </li>
                        <li>
                            <span class="lt_mobile">131*****523</span><span>2016-03-01</span>
                        </li>
                        <li>
                            <span class="lt_mobile">131*****523</span><span>2016-03-01</span>
                        </li>
                        <li>
                            <span class="lt_mobile">131*****523</span><span>2016-03-01</span>
                        </li>
                        <li>
                            <span class="lt_mobile">131*****523</span><span>2016-03-01</span>
                        </li>
                        <li>
                            <span class="lt_mobile">131*****523</span><span>2016-03-01</span>
                        </li>
                        <li>
                            <span class="lt_mobile">131*****523</span><span>2016-03-01</span>
                        </li>
                        <li>
                            <span class="lt_mobile">131*****523</span><span>2016-03-01</span>
                        </li>
                    </ul>

                </div>
 */
/**
 * 
 * $.fn.run = function(){
    var $ul = $(this).find(".lt_list");
    var ulClone = $ul.clone();
    ulClone.css({top:188});
    var h = parseInt($ul.height());
    var _top = parseInt($ul.position().top);
    var timer = null;
    function run(){
        _top -= 3;
        $ul.animate({top:_top});
        if((_top + h) < 0){
            $ul.remove();
            clearInterval(timer);
            $("#lt_move").append(ulClone).run();
           
        }
    }
    timer = setInterval(run,300);
}

$("#lt_move").run();
 */

/**
 * 第二种方式：无缝滚动
 * var $ul = $("#lt_move").find(".lt_list").first();
        $ul.css({position:"absolute",left:0,top:0});
        function rolling($ul){

            var ulClone = $ul.clone();
            ulClone.css({top:188});

            var h = parseInt($ul.height());
            var _top = parseInt($ul.position().top);
            $ul._timer = null;
            var flag = true;
            function run(){
                _top -= 2;
                $ul.css({top:_top});
                if((_top + h) < -10){
                    $ul.remove();
                    clearInterval($ul._timer);

                }
                if((_top + h) < 188 && flag){
                    flag = false;
                    $("#lt_move").append(ulClone);
                    rolling(ulClone);
                }
            }
            $ul._timer = setInterval(run,300);
        }

        rolling($ul);
 */



