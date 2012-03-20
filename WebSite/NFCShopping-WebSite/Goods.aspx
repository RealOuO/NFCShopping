<%@ Page Title="" Language="C#" MasterPageFile="~/Styles/Site.Master" AutoEventWireup="true"
    CodeBehind="Goods.aspx.cs" Inherits="NFCShopping_WebSite.Goods" %>

<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <asp:ListView ID="List_Categories" runat="server" DataKeyNames="categoryID" DataSourceID="EDS_Categories">
        <EmptyDataTemplate>
            <span>No data was returned.</span>
        </EmptyDataTemplate>
        <ItemTemplate>
            <span style="color: #333333;">
                <asp:Label ID="categoryNameLabel" Font-Size="Medium" Font-Bold="true" runat="server"
                    Text='<%# Eval("categoryName") %>' />
                <br />
                <asp:EntityDataSource ID="EDS_SecCategories" runat="server" ConnectionString="name=Sys_DBEntities"
                    DefaultContainerName="Sys_DBEntities" EnableFlattening="False" EntitySetName="SecCategories" AutoGenerateWhereClause="True">
                    <WhereParameters>
                    <asp:QueryStringParameter Name="CategoryID" QueryStringField="categoryID" DefaultValue="1" Type="Int32" />
                    </WhereParameters>
                </asp:EntityDataSource>
                <asp:ListView ID="List_SecCategories" runat="server" DataKeyNames="SecCategoryID" DataSourceID="EDS_SecCategories">
                    <EmptyDataTemplate>
                        <span>No data was returned.</span>
                    </EmptyDataTemplate>
                    <ItemTemplate>
                        <span style="color: #333333;">
                            <asp:Label ID="secCategoryNameLabel" runat="server"
                                Text='<%# Eval("SecCategoryName") %>' />
                            <br />
                            <br />
                        </span>
                    </ItemTemplate>
                </asp:ListView>
                <br />
                <br />
                <br />
            </span>
        </ItemTemplate>
        <LayoutTemplate>
            <div id="itemPlaceholderContainer" runat="server" style="font-family: Verdana, Arial, Helvetica, sans-serif;">
                <span runat="server" id="itemPlaceholder" />
            </div>
            <div style="text-align: center; background-color: #5D7B9D; font-family: Verdana, Arial, Helvetica, sans-serif;
                color: #FFFFFF;">
                <asp:DataPager ID="DataPager1" runat="server">
                    <Fields>
                        <asp:NextPreviousPagerField ButtonType="Button" ShowFirstPageButton="True" ShowNextPageButton="False"
                            ShowPreviousPageButton="False" />
                        <asp:NumericPagerField />
                        <asp:NextPreviousPagerField ButtonType="Button" ShowLastPageButton="True" ShowNextPageButton="False"
                            ShowPreviousPageButton="False" />
                    </Fields>
                </asp:DataPager>
            </div>
        </LayoutTemplate>
    </asp:ListView>
    <asp:EntityDataSource ID="EDS_Categories" runat="server" ConnectionString="name=Sys_DBEntities"
        DefaultContainerName="Sys_DBEntities" EnableFlattening="False" 
        EntitySetName="Categories">
    </asp:EntityDataSource>
    </asp:Content>
