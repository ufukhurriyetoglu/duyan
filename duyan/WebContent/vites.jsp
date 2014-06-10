<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<!-- Bootstrap -->
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css"
	rel="stylesheet" />
<!-- Custom styles for this template -->
<link
	href="http://getbootstrap.com/examples/sticky-footer-navbar/sticky-footer-navbar.css"
	rel="stylesheet" />
<!-- SelectStyle -->
<link href="css/selectstyle.css" rel="stylesheet" />
<title>Vites</title>
<!-- Selectteki secilen cumlenin textarea da goruntulenmesi -->
<script>
	function selectedSentence() {
		var mylist = document.getElementById("name");
		document.getElementById("content").value = mylist.options[mylist.selectedIndex].text;
	}
</script>
<script>
	function executeNer() {
		document.getElementById("field2").value = document
				.getElementById("field1").value;
	}
</script>
</head>
<body>

	<!-- Fixed navbar -->
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.xhtml">DUYAN</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="index.xhtml">Ana Sayfa</a></li>
					<li><a href="#about">Hakkında</a></li>
					<li><a href="#contact">İletişim</a></li>
				</ul>
			</div>
		</div>
	</div>
	<!-- Begin page content -->
	<div class="container">
		<div class="page-header">
			<h1>
				Vites : Türkçe Varlık İsmi Tanımlama Sistemi <span
					class="label label-success">Yeni</span>
			</h1>
			<ul class="breadcrumb">
				<li><a href="index.xhtml">Duyan</a> <span class="divider"></span></li>
				<li class="active">Vites</li>
			</ul>
		</div>
		<div>
			<strong>Örnek Cümle Seçiniz :</strong> <select class="btn btn-danger"
				id="name" name="name" onchange="selectedSentence()">
				<option value="Cumle1 checked">Mustafa Kemal Atatürk ve
					İsmet İnönü, Türkiye Büyük Millet Meclisi açılışı için İstanbul'dan
					gelerek Ankara'da kaldılar.</option>
				<option value="Cumle2">MHP Genel Başkanı Devlet Bahçeli, cumhurbaşkanı seçimi öncesinde ortak çatı aday belirleme temasları çerçevesinde CHP Genel Başkanı Kemal Kılıçdaroğlu ile görüştü.</option>
				<option value="Cumle3">Yapılan düğünde Arda Turan hazır bulundu.</option>
				<option value="Cumle4">MHP Genel Başkanı Devlet Bahçeli geldi.</option>
			</select><span class="caret"></span> <br>

			<form action="DuyanServlet">
				<table>
					<tr>
						<td>
							<!-- <p>
								<strong>Örnek Cümle :</strong> " Mustafa Kemal Atatürk ve İsmet
								İnönü, Türkiye Büyük Millet Meclisi açılışı için İstanbul'dan
								gelerek Ankara'da kaldılar. "
							</p> <br> -->
						</td>

					</tr>
					<tr>
						<td></td>
					</tr>
					<tr>
						<td><br> <textarea class="form-control" rows="4"
								cols="50" id="content" name="content"
								placeholder="Cumle giriniz...">Mustafa Kemal Atatürk ve İsmet İnönü, Türkiye Büyük Millet Meclisi açılışı için İstanbul'dan gelerek Ankara'da kaldılar.</textarea>
							<br></td>
					</tr>
					<tr>
						<td><strong>Sonuç Tipi : </strong> <input type="radio"
							name="outputtype" value="Json">Json <input type="radio"
							name="outputtype" value="Vites" checked>Vites <input
							type="radio" name="outputtype" value="Brat">Brat<br>
							<br></td>
					</tr>
					<tr>
						<td>
							<button onclick="executeNer()" class="btn btn-large btn-primary"
								type="submit">Onayla</button>
						</td>
					</tr>
				</table>
			</form>
			<br>
			<hr>
			<br>
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">Varlık İsimleri Tanımlanan Cümle</h3>
				</div>
				<div class="panel-body">Sonuc buraya gelecek</div>
			</div>
		</div>
	</div>
	<div id="footer">
		<div class="container">
			<p class="text-muted">
				&#169; 2014 <a href="http://seagentdev.ege.edu.tr">Seagent Lab.</a>
				ve <a href="http://www.galaksiya.com">Galaksiya</a>
			</p>
		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="../../dist/js/bootstrap.min.js"></script>
</body>
</html>