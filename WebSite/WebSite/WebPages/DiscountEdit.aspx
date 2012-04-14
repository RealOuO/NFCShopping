<%@ Page Title="" Language="C#" MasterPageFile="~/Styles/Site.Master" AutoEventWireup="true"
    CodeBehind="DiscountEdit.aspx.cs" Inherits="NFCShoppingWebSite.WebPages.DiscountEdit" %>

<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
<div>
    <asp:Label ID="TitleLabel" runat="server" Font-Bold="True" Font-Size="X-Large" ForeColor="Black"
        Text="Label"></asp:Label>
    <asp:Label ID="DiscountIDLabel" runat="server" Text='<%# Eval("discountID") %>' Visible="False">
    </asp:Label>
</div>
    <div>
    </div>
    <asp:Label ID="Label2" runat="server" Text="优惠活动名称"></asp:Label>
    <div>
    </div>
    <asp:TextBox ID="DiscountDescriptionTextBox" runat="server" Height="23px" Width="109px">
    </asp:TextBox>
         <asp:RequiredFieldValidator runat="server" id="reqDiscountDescription" controltovalidate="DiscountDescriptionTextBox"
             errormessage="请输入优惠活动名称" />
    <div>
    </div>
    <asp:Label ID="Label5" runat="server" Text="优惠内容"></asp:Label>
    <div>
    </div>
    <div>
        <div>
            <asp:Label ID="label6" runat="server" Text="开始时间"></asp:Label>
            <div>
                <asp:TextBox ID="StartDateTextBox" runat="server">
                </asp:TextBox>
                <div>
                    <asp:Label ID="Label7" runat="server" Text="结束时间"></asp:Label>
                    <div>
                        <asp:TextBox ID="EndDateTextBox" runat="server">
                        </asp:TextBox>
                    </div>
                </div>
            </div>
        </div>
        <asp:GridView ID="DiscountItemsGridView" runat="server" AutoGenerateColumns="false"
            CellPadding="4" DataKeyNames="id" ForeColor="#333333" GridLines="None" Width="367px"
            onload="DiscountItemsGridView_Load">
            <alternatingrowstyle backcolor="White" forecolor="#284775" />
            <editrowstyle backcolor="#999999" />
            <footerstyle backcolor="#5D7B9D" font-bold="True" forecolor="White" />
            <headerstyle backcolor="#5D7B9D" font-bold="True" forecolor="White" />
            <pagerstyle backcolor="#284775" forecolor="White" horizontalalign="Center" />
            <rowstyle backcolor="#F7F6F3" forecolor="#333333" />
            <selectedrowstyle backcolor="#E2DED6" font-bold="True" forecolor="#333333" />
            <sortedascendingcellstyle backcolor="#E9E7E2" />
            <sortedascendingheaderstyle backcolor="#506C8C" />
            <sorteddescendingcellstyle backcolor="#FFFDF8" />
            <sorteddescendingheaderstyle backcolor="#6F8DAE" />
            <columns>
                        <asp:TemplateField ControlStyle-Width="100px" HeaderText="优惠商品" 
                            ItemStyle-Height="25px">
                            <ItemTemplate>
                                <a href='<%# VirtualPathUtility.ToAbsolute("~/WebPages/ProductDetails.aspx?productID=" + Eval("productID")) %>'>
                                <asp:Label ID="Product" runat="server" Text='<%# Eval("productName") %>'></asp:Label>
                                </a>
                            </ItemTemplate>
                        </asp:TemplateField>
                        <asp:BoundField DataField="description" HeaderStyle-Width="300px" 
                            HeaderText="描述" />
                        <asp:BoundField DataField="discountPercent" HeaderStyle-Width="50px" 
                            HeaderText="折扣" />
                        <asp:TemplateField ControlStyle-Width="100px" HeaderText="操作">
                            <ItemTemplate>
                                <asp:Button ID="Edit" runat="server" CommandName="EditItem" CommandArgument='<%# Eval("id") %>' OnCommand="OnOperateItem" Text="编辑"/>
                            <asp:Button ID="Delete" runat="server" CommandName="DeleteItem" CommandArgument='<%# Eval("id") %>' OnCommand="OnOperateItem" Text="删除"/>
                            </ItemTemplate>
                        </asp:TemplateField>
                    </columns>
        </asp:GridView>
    </div>
    <asp:Button ID="SubmitButton" runat="server" Height="29px" Text="确定" Width="73px"
        OnClick="SubmitButton_Click" />
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <asp:Button ID="InsertItemButton" runat="server" Height="29px" Text="增加优惠商品" Width="103px"
        CommandName="InsertItem" oncommand="OnOperateItem" />
    &nbsp;
<asp:ObjectDataSource ID="DiscountsDataSource" runat="server" DataObjectTypeName="NFCShoppingWebSite.Access_Data.Discount"
        DeleteMethod="DeleteDiscount" InsertMethod="InsertDiscount" SelectMethod="GetDiscountByID"
        TypeName="NFCShoppingWebSite.BLL.DiscountBL" UpdateMethod="UpdateDiscount">
        <selectparameters>
            <asp:QueryStringParameter DefaultValue="-1" Name="discountID" QueryStringField="discountID"
                Type="Int32" />
        </selectparameters>
        <updateparameters>
            <asp:Parameter Name="discount" Type="Object" />
            <asp:Parameter Name="origDiscount" Type="Object" />
        </updateparameters>
    </asp:ObjectDataSource>
    </asp:Content>
