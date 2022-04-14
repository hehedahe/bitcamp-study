<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>MyList!</title>
  <link href="/css/common.css" rel="stylesheet">
</head>
<body>
<div class="container">
<div id="header">
<style>
#login-btn {
  position: absolute;
  right: 10px;
}
#logout-btn {
  position: absolute;
  right: 10px;
}
#app-title {
  font-size: 1.5em;
  font-weight: bold;
  font-style: none;
  color: white;
} 
#user-name {
  position: absolute;
  right: 90px;
}
</style>
<a href="/index.html"><span id="app-title">MyList</span></a> 
<button id="login-btn" type="button" class="not-login">???</button>
<span id="user-name" class="login"></span>
<button id="logout-btn" type="button" class="login">????</button>  
</div>
<div id="sidebar">
<style>
h1.sidebar {
  font-size: 1.2em;
}
</style>
<h1 class="sidebar">??</h1>
<div class="sidebar">
<ul>
  <li>??1</li>
  <li>??2</li>
  <li>??3</li>
</ul>
</div>  
</div>
<div id="content">
<h1>???</h1>
<a href="add">? ???</a>
<table id="x-board-table" border="1">
<thead>
  <tr>
    <th>??</th>
    <th>??</th>
    <th>???</th>
    <th>???</th>
    <th>???</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>13</td>
    <td><a href='detail?no=13'>ddd</a></td>
    <td>user2</td>
    <td>0</td>
    <td>2022-04-13</td>
  </tr>
  <tr>
    <td>12</td>
    <td><a href='detail?no=12'>test</a></td>
    <td>user1</td>
    <td>14</td>
    <td>2022-04-12</td>
  </tr>
</tbody>
</table>
</div>
<div id="footer">
<style>
#company-title {
  font-size: 1.2em;
  font-weight: bold;
}
#company-address {
  display: inline-block;
  width: calc(100% - 100px); 
  text-align: center; 
}
</style>
<span id="company-title">????</span> 
<address id="company-address">?? ??? ????94? 20, ????</address>  
</div>
</div>
</body>
</html>
    