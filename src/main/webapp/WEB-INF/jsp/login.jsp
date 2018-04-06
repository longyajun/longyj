<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
	<meta charset="utf-8" />
    <title>用户登录</title>
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
    <link href="./bootstrap-3.3.0/css/bootstrap.min.css" rel="stylesheet" />
    
    <style type="text/css">
            .loginTitle{
                text-align: center;
                font-family: 微软雅黑;
            }
	</style>
</head>

<body>
  <div class="container">
            <div class="loginTitle">
                <h1>用户、角色、权限管理系统</h1>
            </div>
            <hr />
            <form class="form-horizontal" role="form" method="post" action="./login">
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">用户名</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="inputEmail3" placeholder="请输入用户名" name="username" value="admin"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="inputPassword3" placeholder="请输入密码" name="password" value="123456" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">提交</button>
                    </div>
                </div>
            </form>

            <!-- <span class="label label-danger">${msg}</span> -->

            <br /><br />

            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">项目源码及测试账号</h3>
                </div>
                <div class="panel-body">

                    <table class="table table-condensed">
                        <thead>
                            <tr>
                                <th>用户名</th>
                                <th>密码</th>
                                <th>权限说明</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>admin</td>
                                <td>123456</td>
                                <td>超级管理员,可以执行任何操作</td>
                            </tr>

                            <tr>
                                <td>dev</td>
                                <td>123456</td>
                                <td>开发人员,可以操作角色和资源,有用户查看权限,无删除权限</td>
                            </tr>

                            <tr>
                                <td>test</td>
                                <td>123456</td>
                                <td>测试人员,比开发人员多了删除权限</td>
                            </tr>

                            <tr>
                                <td>guest</td>
                                <td>123456</td>
                                <td>游客,只有查看权限</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>