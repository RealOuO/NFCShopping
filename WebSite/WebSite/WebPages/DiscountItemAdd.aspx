<%@ Page Title="" Language="C#" MasterPageFile="~/Styles/Site.Master" AutoEventWireup="true" CodeBehind="DiscountItemAdd.aspx.cs" Inherits="NFCShoppingWebSite.WebPages.DiscountItemAdd" %>
<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    
    
    
    
    <asp:ObjectDataSource ID="DiscountItemDataSource" runat="server" 
        DataObjectTypeName="NFCShoppingWebSite.Access_Data.DiscountItem" 
        DeleteMethod="DeleteDiscountItem" InsertMethod="InsertDiscountItem" 
        SelectMethod="GetDiscountItemsByDiscountID" 
        TypeName="NFCShoppingWebSite.BLL.DiscountItemBL" 
        UpdateMethod="UpdateDiscountItem">
        <SelectParameters>
            <asp:QueryStringParameter DefaultValue="1" Name="discountID" 
                QueryStringField="discountID" Type="Int32" />
        </SelectParameters>
        <UpdateParameters>
            <asp:Parameter Name="discountItem" Type="Object" />
            <asp:Parameter Name="origDiscountItem" Type="Object" />
        </UpdateParameters>
    </asp:ObjectDataSource>
    <asp:ObjectDataSource ID="ProductDataSource" runat="server" 
        SelectMethod="GetProduct" TypeName="NFCShoppingWebSite.BLL.ProductBL">
        <SelectParameters>
            <asp:QueryStringParameter DefaultValue="1" Name="id" 
                QueryStringField="productID" Type="Int32" />
        </SelectParameters>
    </asp:ObjectDataSource>
    <asp:ObjectDataSource ID="DiscountDataSource" runat="server" 
        SelectMethod="GetDiscounts" TypeName="NFCShoppingWebSite.BLL.DiscountBL">
    </asp:ObjectDataSource>
    <asp:DetailsView ID="DetailsView1" runat="server" AutoGenerateRows="False" 
        DataSourceID="ProductDataSource" Height="50px" Width="343px">
        <Fields>
            <asp:BoundField DataField="productID" HeaderText="productID" 
                SortExpression="productID" />
            <asp:BoundField DataField="secCategoryID" HeaderText="secCategoryID" 
                SortExpression="secCategoryID" />
            <asp:BoundField DataField="barCode" HeaderText="barCode" 
                SortExpression="barCode" />
            <asp:BoundField DataField="productName" HeaderText="productName" 
                SortExpression="productName" />
            <asp:BoundField DataField="price" HeaderText="price" SortExpression="price" />
            <asp:BoundField DataField="brand" HeaderText="brand" SortExpression="brand" />
            <asp:BoundField DataField="location" HeaderText="location" 
                SortExpression="location" />
            <asp:BoundField DataField="imageURL" HeaderText="imageURL" 
                SortExpression="imageURL" />
            <asp:BoundField DataField="description" HeaderText="description" 
                SortExpression="description" />
        </Fields>
    </asp:DetailsView>
    <br />
    <asp:Label ID="Label6" runat="server" Text="优惠期"></asp:Label>
    <br />
    <br />
    <asp:DropDownList ID="DiscountDropDownList" runat="server" 
        DataSourceID="DiscountDataSource" DataTextField="description" 
        DataValueField="discountID">
    </asp:DropDownList>
    <br />
    <br />
    <asp:Label ID="Label2" runat="server" Text="折扣"></asp:Label>
    <br />
    <br />
    <asp:TextBox ID="DiscountPercentTextBox" runat="server"></asp:TextBox>
    <br />
    <br />
    <asp:Label ID="Label3" runat="server" Text="具体描述"></asp:Label>
    <br />
    <br />
    <asp:TextBox ID="DescriptionTextBox" runat="server"></asp:TextBox>
    <br />
    <br />
    <asp:Label ID="Label4" runat="server" Text="起始日期"></asp:Label>
    <br />
    <br />
    <asp:TextBox ID="StartDateTextBox" runat="server"></asp:TextBox>
    <br />
    <br />
    <asp:Label ID="Label5" runat="server" Text="截止日期"></asp:Label>
    <br />
    <br />
    <asp:TextBox ID="EndDateTextBox" runat="server"></asp:TextBox>
    <br />
    <br />
    <asp:Button ID="Button1" runat="server" Text="确认提交" onclick="Button1_Click" />
</asp:Content>
