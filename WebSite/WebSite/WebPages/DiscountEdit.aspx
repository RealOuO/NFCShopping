<%@ Page Title="" Language="C#" MasterPageFile="~/Styles/Site.Master" AutoEventWireup="true"
    CodeBehind="DiscountEdit.aspx.cs" Inherits="NFCShoppingWebSite.WebPages.DiscountEdit" %>

<%@ Register Assembly="DatePicker" Namespace="DatePicker" TagPrefix="picker" %>
<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">
  <script type="text/javascript">
      $(function () {
          $(".children:eq(2)").show();
          $("span:eq(4)").html("-");
          $("a:eq(11)").css({ "color": "red" });
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
    <div>
        <asp:Label ID="TitleLabel" runat="server" Font-Bold="True" Font-Size="X-Large" ForeColor="Black"
            Text="Label"></asp:Label>
        <asp:Label ID="DiscountIDLabel" runat="server" Text='<%# Eval("discountID") %>' Visible="False"></asp:Label>
    </div>
    <div>
    </div>
    <asp:Label ID="Label2" runat="server" Text="优惠活动名称"></asp:Label>
    <div>
    </div>
    <asp:TextBox ID="DiscountDescriptionTextBox" runat="server" Height="23px" Width="109px"
        required="true"></asp:TextBox>
    <div>
    </div>
    <div>
        <div>
            <asp:Label ID="label6" runat="server" Text="开始时间"></asp:Label>
            <div>
                <picker:DatePicker ID="DateDown" runat="server"></picker:DatePicker>
            </div>
        </div>
        <div>
            <asp:Label ID="Label7" runat="server" Text="结束时间"></asp:Label>
            <div>
                <picker:DatePicker ID="DateUp" runat="server"></picker:DatePicker>
            </div>
        </div>
        <asp:GridView ID="DiscountItemsGridView" runat="server" AutoGenerateColumns="false"
            CellPadding="4" DataKeyNames="id" ForeColor="#333333" GridLines="None" Width="367px"
            OnLoad="DiscountItemsGridView_Load">
            <AlternatingRowStyle BackColor="White" ForeColor="#284775" />
            <EditRowStyle BackColor="#999999" />
            <FooterStyle BackColor="#5D7B9D" Font-Bold="True" ForeColor="White" />
            <HeaderStyle BackColor="#5D7B9D" Font-Bold="True" ForeColor="White" />
            <PagerStyle BackColor="#284775" ForeColor="White" HorizontalAlign="Center" />
            <RowStyle BackColor="#F7F6F3" ForeColor="#333333" />
            <SelectedRowStyle BackColor="#E2DED6" Font-Bold="True" ForeColor="#333333" />
            <SortedAscendingCellStyle BackColor="#E9E7E2" />
            <SortedAscendingHeaderStyle BackColor="#506C8C" />
            <SortedDescendingCellStyle BackColor="#FFFDF8" />
            <SortedDescendingHeaderStyle BackColor="#6F8DAE" />
            <Columns>
                <asp:TemplateField ControlStyle-Width="100px" HeaderText="优惠商品" ItemStyle-Height="25px">
                    <ItemTemplate>
                        <a href='<%# VirtualPathUtility.ToAbsolute("~/WebPages/ProductDetails.aspx?productID=" + Eval("productID")) %>'>
                            <asp:Label ID="Product" runat="server" Text='<%# Eval("productName") %>'></asp:Label>
                        </a>
                    </ItemTemplate>
                </asp:TemplateField>
                <asp:BoundField DataField="description" HeaderStyle-Width="300px" HeaderText="描述" />
                <asp:BoundField DataField="discountPercent" HeaderStyle-Width="50px" HeaderText="折扣" />
                <asp:TemplateField ControlStyle-Width="100px" HeaderText="操作">
                    <ItemTemplate>
                        <asp:Button ID="Edit" runat="server" CommandName="EditItem" CommandArgument='<%# Eval("id") %>'
                            OnCommand="OnOperateItem" Text="编辑" />
                        <asp:Button ID="Delete" runat="server" CommandName="DeleteItem" CommandArgument='<%# Eval("id") %>'
                            OnCommand="OnOperateItem" Text="删除" />
                    </ItemTemplate>
                </asp:TemplateField>
            </Columns>
        </asp:GridView>
    </div>
    <br />
    <asp:Button ID="SubmitButton" runat="server" Height="29px" Text="确定" Width="73px"
        OnClick="SubmitButton_Click" />
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <asp:Button ID="InsertItemButton" runat="server" Height="29px" Text="增加优惠商品" Width="103px"
        CommandName="InsertItem" OnCommand="OnOperateItem" />
    &nbsp;<asp:ObjectDataSource ID="DiscountsDataSource" runat="server" DataObjectTypeName="NFCShoppingWebSite.Access_Data.Discount"
        DeleteMethod="DeleteDiscount" InsertMethod="InsertDiscount" SelectMethod="GetDiscountByID"
        TypeName="NFCShoppingWebSite.BLL.DiscountBL" UpdateMethod="UpdateDiscount">
        <SelectParameters>
            <asp:QueryStringParameter DefaultValue="-1" Name="discountID" QueryStringField="discountID"
                Type="Int32" />
        </SelectParameters>
        <UpdateParameters>
            <asp:Parameter Name="discount" Type="Object" />
            <asp:Parameter Name="origDiscount" Type="Object" />
        </UpdateParameters>
    </asp:ObjectDataSource>
</asp:Content>
