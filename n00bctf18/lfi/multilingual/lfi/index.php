<!DOCTYPE html>
<html>
<head>
    <title>Multi-Language</title>
</head>

<?php

$language = "english";

error_reporting(-1);

ini_set('display_errors', 1);

ini_set('open_basedir', getcwd());

if(!empty($_REQUEST['language'])){
    $language = $_REQUEST['language'];
}


$languageFile = 'includes/'.$language.'.txt';
if(!file_exists($languageFile)){
    echo '<strong>ERROR</strong> ';
    echo "Article in '$language' language doesn't exists. You can upload it though.";
    echo "<br>\n";

    $language = 'english';
    $languageFile = 'includes/'.$language.'.txt';
}
?>
<body>
    <p> Article in <?php echo $language;?> language</p>
    <hr>
    <?php require_once($languageFile);?>
    <hr>
    <!-- <br> -->
    <form method="GET" action="#">
        <p>
            View article in other language:
            <input name="language" type="text">
            <input type="submit" value="View it!">
        </p>
    </form>
    <p>Submit/Upload article in new language <a href="upload.php">here</a></p>
</body>
</html>