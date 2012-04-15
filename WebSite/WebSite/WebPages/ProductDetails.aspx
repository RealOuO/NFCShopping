<%@ Page Title="" Language="C#" MasterPageFile="~/Styles/Site.Master" AutoEventWireup="true"
    CodeBehind="ProductDetails.aspx.cs" Inherits="NFCShoppingWebSite.WebPages.ProductDetails" %>

<%@ Register Assembly="System.Web.DataVisualization, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35"
    Namespace="System.Web.UI.DataVisualization.Charting" TagPrefix="asp" %>
<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">
    <style type="text/css">
        .style1
        {
            width: 430px;
        }
        .style2
        {
            width: 363px;
        }
    </style>
    <link href="../css/ProductDetails.css" rel="stylesheet" type="text/css" />
            <script type="text/javascript">
                $(function () {
                    $(".children:eq(0)").show();
                    $("span:eq(0)").html("-");
                    $("a:eq(3)").css({ "color": "red" });
                    $(".head:eq(0)").toggle(function () {
                        $(this).next().hide();
                        $("span:eq(0)").html("+");
                    }, function () {
                        $(this).next().show();
                        $("span:eq(0)").html("-");
                    });
                });
    </script>
    <script  type="text/javascript">
        function delete_confirm(e) {
            if (event.srcElement.type == "submit" && document.all(event.srcElement.name).value == "删除")
                event.returnValue = confirm("确认是否删除？");
        }
        document.onclick = delete_confirm; 
</script> 
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <asp:Label ID="TitleLabel" runat="server" Font-Bold="True" Font-Size="X-Large" ForeColor="Black"
        Text="Label"></asp:Label>
    <div style="height: 22px">
    </div>
             <table>
          <tr>
          <td>
         <asp:Label ID="Label3" runat="server" Text="商品分类:"></asp:Label>
         </td>
         <td>
  <asp:Label ID="CategoryLabel" runat="server" Text="Label"></asp:Label>
    </td>
    <td>
     <asp:Label ID="Label4" runat="server" Text="商品子分类:"></asp:Label>
          </td>
         <td>
 <asp:Label ID="SecCategoryLabel" runat="server" Text="Label"></asp:Label>
    </td>
    <td>
    <asp:Label ID="Label1" runat="server" Text="商品名称:" ></asp:Label>
         </td>
         <td>
    <asp:Label ID="ProductNameLable" runat="server" ></asp:Label>

   
    </td>
    <td>
     <asp:Label ID="Label6" runat="server" Text="价格:"></asp:Label>
        </td>
         <td>
    <asp:Label ID="Price" runat="server" required="true"></asp:Label>
    </td>
    </tr>
    <tr>
    <td>
      <asp:Label ID="Label5" runat="server" Text="条形码:"></asp:Label>
       </td>
         <td>
    <asp:Label ID="Barcode" runat="server" 
                 ontextchanged="BarcodeTextBox_TextChanged"></asp:Label>
    </td>
     <td>
    <asp:Label ID="Label7" runat="server" Text="品牌:"></asp:Label>
       </td>
         <td>
    <asp:Label ID="Brand" runat="server"></asp:Label>
  </td>
  <td>
    <asp:Label ID="Label8" runat="server" Text="产地:"></asp:Label>
      </td>
         <td>
    <asp:Label ID="Location" runat="server"></asp:Label>
    </td>
    </tr>
    </table>
    <div style="height: 12px">
    </div>
   <div style="width:1000; height:230;">
       <div class="describe">
    <br />
    <asp:Label ID="Label2" runat="server" Text="商品描述:"></asp:Label>
          <br />       <br />
    <asp:Label ID="ProductDescription" runat="server" Height="140px" TextMode="MultiLine"
        Width="402px"></asp:Label>
        </div>
    <div class="picture">
        <asp:Label ID="Label10" runat="server" Text="商品图片:"></asp:Label>
       <asp:Image ID="ProductImage" runat="server" Height="230px" ImageAlign="Middle"  Width="330px" />
        </div>
    <table style="width: 100%;">
        <tr>
            <td class="style1">
                &nbsp;
            </td>
            <td class="style2">
                <asp:Button ID="EditButton" runat="server" Height="39px" Text="编辑" Width="105px"
                    OnClick="EditButton_Click" />
            </td>
            <td>
                <asp:Button ID="DeleteButton" runat="server" Height="39px" Text="删除" Width="105px"
                    OnClick="DeleteButton_Click" />
            </td>
        </tr>
        <tr>
            <td class="style1">
                &nbsp;
            </td>
            <td class="style2">
                &nbsp;
            </td>
            <td>
                &nbsp;
            </td>
        </tr>
        <tr>
            <td class="style1">
                &nbsp;
            </td>
            <td class="style2">
                &nbsp;
            </td>
            <td>
                &nbsp;
            </td>
        </tr>
    </table>
    <asp:ObjectDataSource ID="ProductsDataSource" runat="server" DataObjectTypeName="NFCShoppingWebSite.Access_Data.Product"
        DeleteMethod="DeleteProduct" InsertMethod="InsertProduct" SelectMethod="GetProduct"
        TypeName="NFCShoppingWebSite.BLL.ProductBL" UpdateMethod="UpdateProduct">
        <SelectParameters>
            <asp:QueryStringParameter DefaultValue="0" Name="id" QueryStringField="productID"
                Type="Int32" />
        </SelectParameters>
        <UpdateParameters>
            <asp:Parameter Name="product" Type="Object" />
            <asp:Parameter Name="origProduct" Type="Object" />
        </UpdateParameters>
    </asp:ObjectDataSource>
    <asp:ObjectDataSource ID="ReviewsDataSourse" runat="server" DataObjectTypeName="NFCShoppingWebSite.Access_Data.Review"
        DeleteMethod="DeleteReview" SelectMethod="GetReviewsByProductID" TypeName="NFCShoppingWebSite.BLL.ReviewBL">
        <SelectParameters>
            <asp:QueryStringParameter Name="pid" QueryStringField="productID" Type="Int32" />
        </SelectParameters>
    </asp:ObjectDataSource>
    <asp:ScriptManager ID="ScriptManager" runat="server" EnablePartialRendering="true">
    </asp:ScriptManager>
    <asp:UpdatePanel ID="UpdatePane" runat="server">
        <ContentTemplate>
            <asp:ListView ID="ReviewListView" runat="server" AllowPaging="true" DataKeyNames="reviewID"
                DataSourceID="ReviewsDataSourse">                
                <EmptyDataTemplate>
                    未返回数据。
                </EmptyDataTemplate>
               
                <ItemSeparatorTemplate>
                    <br />
                </ItemSeparatorTemplate>
                <ItemTemplate>
                    <li style="background-color: #FFFBD6; color: #333333;">           
                        User:
                        <asp:Label ID="UserLabel" runat="server" Text='<%# Eval("User.userName") %>' />
                        <br />
                        comment:
                        <asp:Label ID="commentLabel" runat="server" Text='<%# Eval("comment") %>' />
                        <br />
                        rating:
                        <asp:Label ID="ratingLabel" runat="server" Text='<%# Eval("rating") %>' />
                        <br />
                        createAt:
                        <asp:Label ID="createAtLabel" runat="server" Text='<%# Eval("createAt") %>' />
                        <br />
                        <asp:Button ID="DeleteButton" runat="server" CommandName="Delete" Text="删除" />
                    </li>
                </ItemTemplate>
                <LayoutTemplate>
                    <ul id="itemPlaceholderContainer" runat="server" style="font-family: Verdana, Arial, Helvetica, sans-serif;">
                        <li runat="server" id="itemPlaceholder" />
                    </ul>
                    <div style="text-align: center; background-color: #FFCC66; font-family: Verdana, Arial, Helvetica, sans-serif;
                        color: #333333;">
                        <asp:DataPager ID="DataPager" runat="server" PageSize="4">
                            <Fields>
                                <asp:NextPreviousPagerField ButtonType="Button" ShowFirstPageButton="True" ShowLastPageButton="True" />
                            </Fields>
                        </asp:DataPager>
                    </div>
                </LayoutTemplate>
            </asp:ListView>
        </ContentTemplate>
    </asp:UpdatePanel>
</asp:Content>
