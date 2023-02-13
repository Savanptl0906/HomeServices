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
		class Customer
		{
			public $name;
			public $address;
			public $rate;
		}
		$i = $_REQUEST['mobile'];		
		$j = $_REQUEST['pmobile'];		
		
		$e=array();
		$q = "select * from customer where mobile = '$i'";

		$q1 = "select avg(rate) from ratting where pr_mobile = '$j'";
	
		//echo $q1;
		$rs = $con->query($q);
		$rs1 = $con->query($q1);

		$n=0;	
		
		while($r = mysqli_fetch_assoc($rs))
		{	
			$e[$n]=new Customer();
			
			$e[$n]->name = $r['name'];
			$e[$n]->address =$r['address'];	
			$e[$n]->rate =0;	
			$n++;
		}
		$n=0;	
		
		while($r = mysqli_fetch_assoc($rs1))
		{	
			$e[$n]->rate = $r["avg(rate)"];
			$n++;
		}
//		echo $q;
		$ans1 = json_encode($e);
		echo $ans1;	
	}
?>