<?php

class ClassName {

    function __construct() {
        usort($array, function ($a, $b) {
            return $a - $b;
        });
    }

}

?>