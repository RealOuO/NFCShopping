<%@ Page Title="" Language="C#" MasterPageFile="~/Styles/Site.Master" AutoEventWireup="true"
    CodeBehind="DiscountAdd.aspx.cs" Inherits="NFCShoppingWebSite.WebPages.DiscountAdd" %>

<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <asp:Label ID="Label1" runat="server" Text="优惠描述"></asp:Label>
    <div>
    </div>
    <asp:TextBox ID="DiscountDescriptionTextBox" runat="server" Height="140px" TextMode="MultiLine"
        Width="442px"></asp:TextBox>
    <asp:GridView ID="GridView1" runat="server" AutoGenerateColumns="False" 
        DataSourceID="ObjectDataSource1">
        <Columns>
            <asp:CommandField ShowDeleteButton="True" ShowEditButton="True" />
            <asp:BoundField DataField="discountID" HeaderText="discountID" 
                SortExpression="discountID" />
            <asp:BoundField DataField="description" HeaderText="description" 
                SortExpression="description" />
            <asp:BoundField DataField="createdAt" HeaderText="createdAt" 
                SortExpression="createdAt" />
        </Columns>
    </asp:GridView>
    <div>
    </div>
    <asp:Label ID="Label2" runat="server" Text="起始日期"></asp:Label>
    <div>
    </div>
    <asp:TextBox ID="CreatedTextBox" runat="server"></asp:TextBox>
    <div>
    </div>
     <asp:Button ID="SubmitButton" runat="server" Height="48px" OnClick="SubmitButton_Click"
        Text="确定提交" Width="136px" />
    <asp:ObjectDataSource ID="ObjectDataSource1" runat="server" 
        DataObjectTypeName="NFCShoppingWebSite.Access_Data.Discount" 
        DeleteMethod="DeleteDiscount" InsertMethod="InsertDiscount" 
        SelectMethod="GetDiscounts" TypeName="NFCShoppingWebSite.BLL.DiscountBL" 
        UpdateMethod="UpdateDiscount">
        <UpdateParameters>
            <asp:Parameter Name="discount" Type="Object" />
            <asp:Parameter Name="origDiscount" Type="Object" />
        </UpdateParameters>
    </asp:ObjectDataSource>
</asp:Content>
