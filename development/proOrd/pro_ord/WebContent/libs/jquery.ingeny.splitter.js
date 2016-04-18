(function ($) {

    
    function dropHeight(element,offsetHeight){
        offsetHeight == undefined ? offsetHeight = 0 : null;
        wheight = $(window).height();
//        wtop = $(element).offset().top + offsetHeight; 
//        $(element).height(wheight-wtop);
        $(element).height(wheight);
    }
    
    $.fn.dropToFloor = function(offsetHeight){
        element = this;
        
        $(window).bind('resize',function(){
            dropHeight(element,offsetHeight); 
               
        });   
        $(window).resize();
    }
        
    $.fn.splitter = function(options){
        pane = this;
        minwidth = options.minwidth;
        maxwidth = options.maxwidth;
        defwidth = options.defwidth;
        collapse = options.collapse;
        
        $(this).find("div").css({
            "float":"left",
            "margin":"0",
            "padding":"0",
        });
       
        $(this).parent().append("<div id=\"sliderOverlay\" style=\"position:absolute;top:0px;left:0px;\"></div>");
    	$("#sliderOverlay").hide();
    	
        dropHeight(pane);
        
        $(window).bind('resize',function(){
            handleResize(pane,minwidth,defwidth);
        });   
        $(window).resize();
        
        // move the splitter
    	$(this).find(".splitter").mousedown(function (e) {
            splitter = this;
            leftDiv = $(splitter).prev('div');
            rightDiv = $(splitter).next('div');	
            oRight = rightDiv.offset().left + rightDiv.outerWidth();	
            
            $(splitter).mouseup(function(){
                stopDrag();
            });
            $(splitter).css({'zIndex':"900","position":"relative"});
            $("#sliderOverlay").css({"height":"100%","width":"100%"}).show();
            $("#sliderOverlay").bind('mousemove', function (e) {
    		      startDrag(e,splitter);               
    		});
            $("select").bind('mousemove', function (e) {
    		      startDrag(e,splitter);               
    		});
    		    		
  	         
    		$('body').css('cursor', 'w-resize');
    		e.preventDefault();
    	});
    	
    	jQuery.each(collapse,function(k,v){
    	   
            if ( v =="left"){
                splitter = $("#"+k).next(".splitter")

            	splitter.dblclick(function () {
            	    stopDrag();
                    leftDiv = $(this).prev('div');
                    rightDiv = $(this).next('div');	
                    if (leftDiv.width() > 0){
                        leftDiv.animate({width:"0"},400);
                        rightDiv.animate({width: (rightDiv.width() + leftDiv.width()  ) },400);
                    }else{
                        rightDiv.animate({width: (rightDiv.width() - defwidth[k]  ) },400); 
                        leftDiv.animate({width: defwidth[k]},400);
                                           
                    }
                });            
            }
            
            if (v == "right"){
                splitter =  $("#"+k).prev(".splitter");
                
                splitter.dblclick(function () {
                    leftDiv = $(this).prev('div');
                    rightDiv = $(this).next('div');	
                    if (rightDiv.width() > 0){
                        rightDiv.animate({width:"0"},400);
                        leftDiv.animate({width: (leftDiv.width() + rightDiv.width()  ) },400);
                    }else{
                        leftDiv.animate({width: (leftDiv.width() - defwidth[k]  ) },400); 
                        rightDiv.animate({width: defwidth[k]},400);                  
                    }
                }); 
            }

        });
    
    	$("#sliderOverlay").bind('mouseup',function(){
            stopDrag();
        }); 
    	$("select").bind('mouseup',function(){
            stopDrag();
        });         
                

    }
    function stopDrag(){
            $("select").unbind('mousemove');
            $("#sliderOverlay").unbind('mousemove');
            $("#sliderOverlay").css({"height":"0","width":"0"}).hide();
            $('body').css('cursor', 'default');
            setRightWidth();
    }
    function setRightWidth() {
    	var winWidth = $(window).width();
    	var leftWidth = $('div#left').width();
    	var rightWidth = winWidth - leftWidth-5;
    	$('#left iframe').width(leftWidth);
    	$('#right iframe').width(rightWidth);
    }
    function startDrag(e,splitter){
        rightWidth =  oRight - e.pageX - $(splitter).outerWidth();
        leftWidth =   e.pageX - leftDiv.offset().left;
        
        
        lid = leftDiv.attr("id");
        rid = rightDiv.attr("id");
        minwidth[lid] ? leftMin = minwidth[lid] : leftMin = 10;
        minwidth[rid] ? rightMin = minwidth[rid] : rightMin = 10;
        maxwidth[lid] ? leftMax = maxwidth[lid] : leftMax = 2000;
        maxwidth[rid] ? rightMax = maxwidth[rid] : rightMax = 2000;
        
        if (rightWidth >= rightMin && leftWidth >= leftMin && rightWidth <= rightMax && leftWidth <= leftMax){
            rightDiv.width(rightWidth);
            leftDiv.width(leftWidth);
        }else{
            if (rightWidth < rightMin){
                rightDiv.width(rightMin);
                leftDiv.width(leftWidth - (rightMin - rightWidth) ) ;    
            }
            if (leftWidth < leftMin){
                leftDiv.width(leftMin);
                rightDiv.width(rightWidth - (leftMin - leftWidth) ) ;                    
            }
            if (rightWidth > rightMax){
                rightDiv.width(rightMax);
                leftDiv.width(leftWidth - (rightMax - rightWidth)) ;                    
            }  
            if (leftWidth > leftMax){
                leftDiv.width(leftMax);
                rightDiv.width(rightWidth - (leftMax - leftWidth) ) ;                    
            }    
                            
        }
        setRightWidth();
    }
    
    function handleResize(pane,minwidth,defwidth){
        parentwidth = $(pane).innerWidth();
        unset = new Array();
        usedWidth = 0;
        

        $("#" + pane.attr("id") + " > div").each(function(){
            if ($(this).hasClass('splitter')){
                usedWidth += $(this).outerWidth(); 
            }else{
                if (defwidth[this.id]){
                    $("#"+this.id).width(defwidth[this.id]);
                    usedWidth += defwidth[this.id];              
                }else{
                    unset[unset.length] = this.id;
                }              
            }
            dropHeight(this);
        });
                    
        availablewidth = parentwidth - usedWidth;
        splitwidth = Math.floor(availablewidth / unset.length) -1;
        splitwidth < 10 ? splitwidth = 10 : null;
        
        jQuery.each(unset,function(i){
            $("#"+this).width(splitwidth);
        });  
        setRightWidth();
    }

})(jQuery);