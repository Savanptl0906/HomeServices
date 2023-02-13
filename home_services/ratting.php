<?php	
	$sname="localhost";
	$uname="root";
	$psd="";
	$dbname="home_services";
	$con=new mysqli($sname,$uname,$psd,$dbname);
	if($con->connect_error)
	{
		die("Connection error");
	}
	else
	{
	//	echo "connecion done...";
			
		$pr_mobile = $_POST['pr_mobile'];			
		$cr_mobile = $_POST['c_mobile'];
		$rate = $_POST['ratting'];
		$review = $_POST['review'];
		$q = "insert into ratting values('$pr_mobile','$cr_mobile','$rate','$review')";
		
		if($con->query($q))
		{
			echo "success";
		}
		else
		{
			echo "failed";
		}
	}
?>