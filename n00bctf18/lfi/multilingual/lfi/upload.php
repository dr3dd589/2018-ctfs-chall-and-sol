<!DOCTYPE html>
<html>
<head>
    <title>Upload in new language</title>
</head>
<body>
<?php
error_reporting(-1);

ini_set('display_errors', 1);
if (empty($_REQUEST['language']) || empty($_REQUEST['article'])):
?>
<form method="POST" action="#">
    Language: <input type="text" name="language">
    &nbsp;&nbsp;<input type="submit" value="Upload new article">
    <br>
    Article in new language:
    <br>
    <textarea rows="40" cols="120" name="article"></textarea>
</form>
<?php
else:
    $language = $_REQUEST['language'];

    if(stripos($language, '..') !== false || stripos($language, '/') !== false ){
        exit("Invalid language name. No hacking here");
    }
    $article = $_REQUEST['article'];

    if(file_exists("includes/$language.txt")){
        exit("Article already exists in given language");
    }
    $fp = fopen($_SERVER['includes'] . "/$language.txt","wb");
    fwrite($fp,$article);
    fclose($fp);
    
    // if(file_put_contents("includes/$language.txt", $article) === FALSE){
    //     exit("Some error occured while saving article to file system");
    // }

    $status = system("./changeOwner '".escapeshellarg("$language.txt")."'");

    if($status !== "OK"){
        exit("Some unkown error occured");
    }
    else{
        exit("Uploaded");
    }
endif;
?>
</body>
</html>