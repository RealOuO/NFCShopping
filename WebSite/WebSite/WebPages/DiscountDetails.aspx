<%@ Page Title="" Language="C#" MasterPageFile="~/Styles/Site.Master" AutoEventWireup="true"
    CodeBehind="DiscountDetails.aspx.cs" Inherits="NFCShoppingWebSite.WebPages.DiscountDetails" %>

<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <asp:ObjectDataSource ID="DiscountsDataSource" runat="server" DataObjectTypeName="NFCShoppingWebSite.Access_Data.Discount"
        DeleteMethod="DeleteDiscount" InsertMethod="InsertDiscount" SelectMethod="GetDiscountByID"
        TypeName="NFCShoppingWebSite.BLL.DiscountBL" UpdateMethod="UpdateDiscount">
        <SelectParameters>
            <asp:QueryStringParameter DefaultValue="0" Name="discountID" QueryStringField="discountID"
                Type="Int32" />
        </SelectParameters>
        <UpdateParameters>
            <asp:Parameter Name="discount" Type="Object" />
            <asp:Parameter Name="origDiscount" Type="Object" />
        </UpdateParameters>
    </asp:ObjectDataSource>
    <asp:ListView ID="ListView1" runat="server" DataSourceID="DiscountsDataSource">
        <EmptyDataTemplate>
            没有找到该优惠活动
        </EmptyDataTemplate>
        <ItemSeparatorTemplate>
        </ItemSeparatorTemplate>
        <ItemTemplate>
            <div>
                <div>
                    <asp:Label ID="TitleLabel" runat="server" Font-Bold="True" Font-Size="X-Large" ForeColor="Black"
                        Text='<%# Eval("description") %>'></asp:Label>
                    <div>
                    </div>
                    <asp:Label ID="CreatingDateLabel" runat="server" Text='<%# Eval("createdAt") %>'></asp:Label>
                    <div>
                    </div>
                </div>
            </div>
            <br />
            <asp:Label ID="Label1" runat="server" Text="当前优惠活动包含以下优惠商品："></asp:Label>
            <div>
            <br />
            </div>
            <asp:GridView ID="DiscountItemsListView" runat="server" DataSource='<%# Eval("discountItems") %>'
                AutoGenerateColumns="false" ForeColor="#333333" GridLines="None" HeaderStyle-Height="50px">
                <AlternatingRowStyle BackColor="White" ForeColor="#284775" />
                <EditRowStyle BackColor="#999999" />
                <FooterStyle BackColor="#5D7B9D" Font-Bold="True" ForeColor="White" />
                <HeaderStyle BackColor="#5D7B9D" Font-Bold="True" ForeColor="White" />
                <PagerStyle BackColor="#284775" ForeColor="White" HorizontalAlign="Center" />
                <RowStyle BackColor="#D3D3E3" ForeColor="#333333" />
                <SelectedRowStyle BackColor="#E2DED6" Font-Bold="True" ForeColor="#333333" />
                <SortedAscendingCellStyle BackColor="#E9E7E2" />
                <SortedAscendingHeaderStyle BackColor="#506C8C" />
                <SortedDescendingCellStyle BackColor="#FFFDF8" />
                <SortedDescendingHeaderStyle BackColor="#6F8DAE" />
                <Columns>
                    <asp:TemplateField HeaderText="优惠商品" ControlStyle-Width="100px" ItemStyle-Height="25px">
                        <ItemTemplate>
                            <a href='<%# VirtualPathUtility.ToAbsolute("~/WebPages/ProductDetails.aspx?productID=" + Eval("Product.productID")) %>'>
                                <asp:Label ID="ProductNameLabel" runat="server" Text='<%# Eval("Product.productName") %>'></asp:Label>
                            </a>
                        </ItemTemplate>
                    </asp:TemplateField>
                    <asp:BoundField HeaderText="描述" DataField="description" HeaderStyle-Width="300px" />
                    <asp:BoundField HeaderText="折扣" DataField="discountPercent" HeaderStyle-Width="50px" />
                    <asp:TemplateField HeaderText="操作" HeaderStyle-Width="100px">
                        <ItemTemplate>
                            <a href='<%# VirtualPathUtility.ToAbsolute("~/WebPages/DiscountItemEdit.aspx?isNew=false&discountItemID=" + Eval("id")) %>'>
                                编辑 </a>
                        </ItemTemplate>
                    </asp:TemplateField>
                </Columns>
            </asp:GridView>
            <div>
            <br />
            </div>
            <asp:Button ID="EditButton" runat="server" Text="编辑" Height="39px" Width="105px" /><asp:Button ID="DeleteButton" runat="server"
                Text="删除" Height="39px" Width="105px" />
        </ItemTemplate>
    </asp:ListView>
</asp:Content>
