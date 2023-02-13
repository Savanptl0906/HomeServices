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
		class Request
		{
			public $customername;
			public $customermobile;
			public $customeraddress;
			public $model;
			public $issue;
			public $pname;
			public $providercharge;
			public $prmobile;
			

		}
		$i = $_REQUEST['providermobile'];		
		$e=array();
		$q = "select * from request where providermobile = '$i' and status='pending'";
		$rs = $con->query($q);
		$n=0;	
		
		while($r = mysqli_fetch_assoc($rs))
		{	
			$e[$n]=new Request();
		
			$e[$n]->customername = $r['customername'];
			$e[$n]->customermobile = $r['customermobile'];
			$e[$n]->customeraddress = $r['customeraddress'];
			$e[$n]->model = $r['model'];
			$e[$n]->issue = $r['issue'];			
			$e[$n]->pname = $r['shopname'];			
			$e[$n]->providercharge = $r['providercharge'];
			$e[$n]->prmobile = $r['providermobile'];
			$n++;
		}
//		echo $q;
		$ans1 = json_encode($e );
		echo $ans1;	
	}
?>