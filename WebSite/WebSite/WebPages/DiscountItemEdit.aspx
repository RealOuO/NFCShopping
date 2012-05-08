<%@ Page Title="" Language="C#" MasterPageFile="~/Styles/Site.Master" AutoEventWireup="true"
    CodeBehind="DiscountItemEdit.aspx.cs" Inherits="NFCShoppingWebSite.WebPages.DiscountItemEdit" %>

<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">
  <script type="text/javascript">
      $(function () {
          $(".children:eq(2)").show();
          $("span:eq(4)").html("-");
          $("a:eq(12)").css({ "color": "red" });
          $(".head:eq(2)").toggle(function () {
              $(this).next().hide();
              $("span:eq(4)").html("+");
          }, function () {
              $(this).next().show();
              $("span:eq(4)").html("-");
          });
      });

    </script>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <asp:ObjectDataSource ID="DiscountItemsDataSource" runat="server" DataObjectTypeName="NFCShoppingWebSite.Access_Data.DiscountItem"
        DeleteMethod="DeleteDiscountItem" InsertMethod="InsertDiscountItem" SelectMethod="GetDiscountItem"
        TypeName="NFCShoppingWebSite.BLL.DiscountItemBL" UpdateMethod="UpdateDiscountItem">
        <SelectParameters>
            <asp:QueryStringParameter DefaultValue="-1" Name="id" QueryStringField="discountItemID"
                Type="Int32" />
        </selectparameters>
        <updateparameters>
            <asp:Parameter Name="discountItem" Type="Object" />
            <asp:Parameter Name="origDiscountItem" Type="Object" />
        </updateparameters>
    </asp:ObjectDataSource>
    <asp:ObjectDataSource ID="DiscountsDataSource" runat="server" SelectMethod="GetDiscounts"
        TypeName="NFCShoppingWebSite.BLL.DiscountBL"></asp:ObjectDataSource>
    <asp:ObjectDataSource ID="ProductDataSource" runat="server" SelectMethod="GetProduct"
        TypeName="NFCShoppingWebSite.BLL.ProductBL">
        <SelectParameters>
            <asp:ControlParameter ControlID="ProductsDropDownList" Name="id" PropertyName="SelectedValue"
                Type="Int32" />
        </SelectParameters>
    </asp:ObjectDataSource>
    <asp:Label ID="TitleLabel" runat="server" Text="Label" Font-Bold="True" Font-Size="X-Large"
        ForeColor="Black"></asp:Label>
    <asp:DetailsView ID="ProductDetailsView" runat="server" AutoGenerateRows="false"
        BackColor="White" BorderColor="#E7E7FF" BorderStyle="None" BorderWidth="1px"
        DataSourceID="ProductDataSource" GridLines="Horizontal" Height="52px"
        Width="482px">
        <Fields>
            <asp:BoundField DataField="productID" HeaderText="商品ID号" SortExpression="productID" />
            <asp:BoundField DataField="barCode" HeaderText="商品条形码" SortExpression="barCode" />
            <asp:BoundField DataField="productName" HeaderText="商品名" SortExpression="productName" />
            <asp:BoundField DataField="price" HeaderText="价格" SortExpression="price" />
            <asp:BoundField DataField="brand" HeaderText="品牌" SortExpression="brand" />
            <asp:BoundField DataField="location" HeaderText="生产地" SortExpression="location" />
            <asp:BoundField DataField="description" HeaderText="商品描述" SortExpression="description" />
        </Fields>
        <FooterStyle BackColor="#B5C7DE" ForeColor="#4A3C8C" />
        <HeaderStyle BackColor="#4A3C8C" ForeColor="#F7F7F7"  HorizontalAlign="Center"/>
        <PagerStyle BackColor="#E7E7FF" ForeColor="#4A3C8C" HorizontalAlign="Right" />
        <RowStyle BackColor="#E7E7FF" ForeColor="#4A3C8C"  />
    </asp:DetailsView>
    <br />
    <asp:Label ID="Label2" runat="server" Text="请输入关键字"></asp:Label>
    <div>
        <asp:TextBox ID="ProductNameTextBox" runat="server">
        </asp:TextBox>
        <asp:DropDownList ID="ProductsDropDownList" runat="server" DataTextField="productID"
            DataValueField="productID">
        </asp:DropDownList>
        <asp:Button ID="SearchButton" runat="server" Text="搜索" onclick="SearchButton_Click" />
        <asp:RequiredFieldValidator runat="server" id="reqProductName" controltovalidate="ProductNameTextBox"
            errormessage="请输入商品名称" />
    </div>
    <asp:Label ID="Label6" runat="server" Text="商品名称"></asp:Label>
    <br />
    <asp:DropDownList ID="ProductsDropDownList" runat="server" DataTextField="productName"
        DataValueField="productID" Height="20px" Width="152px" AutoPostBack="True">
    </asp:DropDownList>
    <br />
    <div>
        <asp:Label ID="Label5" runat="server" Text="添加到该优惠活动"></asp:Label>
        <div>
            <asp:DropDownList ID="DiscountsDropDownList" runat="server" DataSourceID="DiscountsDataSource"
                DataTextField="description" DataValueField="discountID" OnDataBound="DiscountsDropDownList_DataBound">
            </asp:DropDownList>
        </div>
    </div>
    <br />
    <asp:Label ID="Label3" runat="server" Text="折扣"></asp:Label>
    <br />
    <br />
    <asp:TextBox ID="DiscountPercentTextBox" runat="server" Text='<%# Eval("discountPercent") %>'>
    </asp:TextBox>
     <asp:RequiredFieldValidator runat="server" id="reqDiscountPercent" controltovalidate="DiscountPercentTextBox"
            errormessage="请输入折扣信息" />
     <br />

     <asp:RangeValidator runat="server" id="rngDiscontPercent" controltovalidate="DiscountPercentTextBox"
         type="Double" minimumvalue="0.0" maximumvalue="1.0" errormessage="请输入一个合理的折扣" />
    <br />
    <br />
    <asp:Label ID="Label4" runat="server" Text="描述信息"></asp:Label>
    <br />
    <br />
    <asp:TextBox ID="DiscountItemDescriptionTextBox" Text='<%# Eval("description") %>'
        runat="server" Height="140px" TextMode="MultiLine" Width="442px">
    </asp:TextBox>
    <asp:RequiredFieldValidator runat="server" id="reqDiscountItemDescription" controltovalidate="DiscountItemDescriptionTextBox"
            errormessage="请输入具体描述信息" />
    <br />
    <br />
    <asp:Button ID="SubmitButton" runat="server" Text="确定" OnClick="SubmitButton_Click"
        Width="86px" />
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <asp:Button ID="CancelButton" runat="server" Text="取消" OnClick="CancelButton_Click"
        Width="87px" />
    <br />
    <br />
</asp:Content>
