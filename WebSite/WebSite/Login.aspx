<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Login.aspx.cs" Inherits="NFCShoppingWebSite.Login" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>NFCShopping管理平台</title>
    <style type="text/css">
        h1
        {
            color: #666666;
            text-transform: none;
            font-weight: 200;
            margin-bottom: 0px;
        }
        body
        {
            background-color: #deebf3;
            margin: 0;
            padding: 0;
        }
        
        #container
        {
            width: 100%;
            height: 100%;
        }
        
        #bgContainer
        {
            position: relative;
            margin: 0 auto;
            width: 1024px;
            height: 631px;
            background: url(../Images/login_bg.jpg);
        }
        
        #loginContainer
        {
            position: relative;
            padding-top: 30px;
            margin: 0 auto;
            width: 430px;
        }
        
        .clear
        {
            clear: both;
        }
        
        .title
        {
            display: block;
            float: left;
            text-align: left;
            width: auto;
        }
        
        .loginDisplay
        {
            font-size: 1.1em;
            display: block;
            text-align: right;
            padding: 10px 20px 20px;
            color: White;
        }
        
        .loginDisplay a:link
        {
            color: white;
        }
        .login
        {
            min-height: 190px;
            border: 5px solid #4A5A8A;
            -moz-border-radius: 11px;
            -khtml-border-radius: 11px;
            -webkit-border-radius: 11px;
            border-radius: 9px;
        }
        .loginDisplay a:visited
        {
            color: white;
        }
        
        .loginDisplay a:hover
        {
            color: white;
        }
        
        .failureNotification
        {
            font-size: 1.2em;
            color: Red;
        }
        
        .bold
        {
            font-weight: bold;
        }
        
        .submitButton
        {
            text-align: right;
            padding-right: 10px;
        }
    </style>
</head>
<body>
    <form id="form" runat="server">
    <div id="container">
        <div id="bgContainer">
            <div id="loginContainer">
                <h1>
                    NFC Shopping 超市管理平台
                </h1>
                <p>
                
                </p>
                <asp:Login ID="LoginUser" runat="server" EnableViewState="false" RenderOuterTable="false">
                    <LayoutTemplate>
                        <span class="failureNotification">
                            <asp:Literal ID="FailureText" runat="server"></asp:Literal>
                        </span>
                        <asp:ValidationSummary ID="LoginUserValidationSummary" runat="server" CssClass="failureNotification"
                            ValidationGroup="LoginUserValidationGroup" />
                        <div class="accountInfo">
                            <fieldset class="login">
                                <legend>管理员登录</legend>
                                <p>
                                    <asp:Label ID="UserNameLabel" runat="server" AssociatedControlID="UserName">用户名:</asp:Label>
                                    <asp:TextBox ID="UserName" runat="server" CssClass="textEntry"></asp:TextBox>
                                    <asp:RequiredFieldValidator ID="UserNameRequired" runat="server" ControlToValidate="UserName"
                                        CssClass="failureNotification" ErrorMessage="必须填写“用户名”。" ToolTip="必须填写“用户名”。"
                                        ValidationGroup="LoginUserValidationGroup">*</asp:RequiredFieldValidator>
                                </p>
                                <p>
                                    <asp:Label ID="PasswordLabel" runat="server" AssociatedControlID="Password">密码:&nbsp;&nbsp;</asp:Label>
                                    <asp:TextBox ID="Password" runat="server" CssClass="passwordEntry" TextMode="Password"></asp:TextBox>
                                    <asp:RequiredFieldValidator ID="PasswordRequired" runat="server" ControlToValidate="Password"
                                        CssClass="failureNotification" ErrorMessage="必须填写“密码”。" ToolTip="必须填写“密码”。" ValidationGroup="LoginUserValidationGroup">*</asp:RequiredFieldValidator>
                                </p>
                                <p>
                                    <asp:CheckBox ID="RememberMe" runat="server" />
                                    <asp:Label ID="RememberMeLabel" runat="server" AssociatedControlID="RememberMe" CssClass="inline">保持登录状态</asp:Label>
                                </p>
                                <p class="submitButton">
                                    <asp:Button ID="LoginButton" runat="server" CommandName="Login" Text="登录" ValidationGroup="LoginUserValidationGroup" />
                                </p>
                            </fieldset>
                        </div>
                    </LayoutTemplate>
                </asp:Login>
                <p>
                    <asp:HyperLink ID="FindPasswordHyperLink" runat="server" EnableViewState="false">忘记密码</asp:HyperLink>
                </p>
            </div>
        </div>
    </div>
    </form>
</body>
</html>
