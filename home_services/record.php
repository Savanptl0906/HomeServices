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
//		echo "Connection done";
		
		$pr_name=$_POST['pr_name'];
		$pr_mobile=$_POST['pr_mobile'];
		
		$cname=$_POST['cname'];
		$cmobile=$_POST['cmobile'];
		$caddress=$_POST['caddress'];
		$model=$_POST['model'];
		$issue=$_POST['issue'];
		$visitcharge=$_POST['visitcharge'];
		
		$sname=$_POST['sname'];
		$smobile=$_POST['smobile'];
		
		$status="pending";
		
		$q="insert into recored values('$pr_name','$pr_mobile','$cname','$cmobile','$caddress','$model','$issue','$visitcharge','$sname','$smobile','$status')";

		$q1 = "select * from customer_tokens where mobile='$cmobile'";
		if($con->query($q))
		{
			echo "insertion done";
			  $rs=$con->query($q1);
			if($r = mysqli_fetch_assoc($rs))
			{
			$token = $r['token'];
			echo $token;
			send_notification($token,$pr_mobile,$pr_name);
			}	
		}
		else
		{
			echo "insertion failed";
		}
	}
	
	function send_notification($token,$pr_mobile,$pr_name)
{
	define( 'API_ACCESS_KEY', 'AAAAeRS_C4k:APA91bH1ic3hV8D7siiv4w5nWH0Xo-YMn1_IfDU9z5mPaP7TJMuX2s2FuZpq0xug9s3FF3dpYf7GF97Y8wgkN9bv7t4zfH33v5GFQ9t2Mftjfxrn-UwaKdO1FQJ9MhKUm9ey72pPa7Fq');

     $msg = array
          (
		'body' 	=> "please contact on $pr_mobile for any query", 
		'title'	=> "Service Request accepted from $pr_name.",
		'click_action' => 'OPEN_ACTIVITY_1',
          );

          $data = array
          (
          	
          	'body' 	=> "please contact on $pr_mobile for any query", 
			'title'	=> "Service Request accepted from $pr_name.",
			'click_action' => 'OPEN_ACTIVITY_1',
          );

	$fields = array
			(
				'to'		=> $token,
				'data'	=> $data,
				'notification'	=> $msg,
            );
  $headers = array
			(
				'Authorization: key=' . API_ACCESS_KEY,
				'Content-Type: application/json'
			);

		$ch = curl_init();
		curl_setopt( $ch,CURLOPT_URL, 'https://fcm.googleapis.com/fcm/send' );
		curl_setopt( $ch,CURLOPT_POST, true );
		curl_setopt( $ch,CURLOPT_HTTPHEADER, $headers );
		curl_setopt( $ch,CURLOPT_RETURNTRANSFER, true );
		curl_setopt( $ch,CURLOPT_SSL_VERIFYPEER, false );
		curl_setopt( $ch,CURLOPT_POSTFIELDS, json_encode( $fields ) );
		$result = curl_exec($ch );
		echo $result;
		curl_close( $ch );

}
?>