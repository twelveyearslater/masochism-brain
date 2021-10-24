<%--
  Created by IntelliJ IDEA.
  User: xiaoc
  Date: 2019/9/27
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8" />
    <title>Hyper - Responsive Bootstrap 4 Admin Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
    <meta content="Coderthemes" name="author" />
    <!-- App favicon -->
    <link rel="shortcut icon" href="assets/images/favicon.ico">

    <!-- third party css -->
    <link href="assets/css/vendor/dataTables.bootstrap4.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/vendor/responsive.bootstrap4.css" rel="stylesheet" type="text/css" />
    <!-- third party css end -->

    <!-- App css -->
    <link href="assets/css/icons.min.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/app.min.css" rel="stylesheet" type="text/css" />

</head>

<body>

<!-- Topbar Start -->
<div class="navbar-custom topnav-navbar">
    <div class="container-fluid">

        <!-- LOGO -->
        <a href="index.html" class="topnav-logo">
                    <span class="topnav-logo-lg">
                        <img src="assets/images/logo-light.png" alt="" height="16">
                    </span>
            <span class="topnav-logo-sm">
                        <img src="assets/images/logo_sm.png" alt="" height="16">
                    </span>
        </a>

        <ul class="list-unstyled topbar-right-menu float-right mb-0">

            <li class="dropdown notification-list topbar-dropdown d-none d-lg-block">
                <a class="nav-link dropdown-toggle arrow-none" data-toggle="dropdown" id="topbar-languagedrop" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                    <img src="assets/images/flags/us.jpg" alt="user-image" class="mr-1" height="12"> <span class="align-middle">English</span> <i class="mdi mdi-chevron-down"></i>
                </a>
                <div class="dropdown-menu dropdown-menu-right dropdown-menu-animated topbar-dropdown-menu" aria-labelledby="topbar-languagedrop">

                    <!-- item-->
                    <a href="javascript:void(0);" class="dropdown-item notify-item">
                        <img src="assets/images/flags/germany.jpg" alt="user-image" class="mr-1" height="12"> <span class="align-middle">German</span>
                    </a>

                    <!-- item-->
                    <a href="javascript:void(0);" class="dropdown-item notify-item">
                        <img src="assets/images/flags/italy.jpg" alt="user-image" class="mr-1" height="12"> <span class="align-middle">Italian</span>
                    </a>

                    <!-- item-->
                    <a href="javascript:void(0);" class="dropdown-item notify-item">
                        <img src="assets/images/flags/spain.jpg" alt="user-image" class="mr-1" height="12"> <span class="align-middle">Spanish</span>
                    </a>

                    <!-- item-->
                    <a href="javascript:void(0);" class="dropdown-item notify-item">
                        <img src="assets/images/flags/russia.jpg" alt="user-image" class="mr-1" height="12"> <span class="align-middle">Russian</span>
                    </a>

                </div>
            </li>

            <li class="dropdown notification-list">
                <a class="nav-link dropdown-toggle arrow-none" data-toggle="dropdown" href="#" id="topbar-notifydrop" role="button" aria-haspopup="true" aria-expanded="false">
                    <i class="dripicons-bell noti-icon"></i>
                    <span class="noti-icon-badge"></span>
                </a>
                <div class="dropdown-menu dropdown-menu-right dropdown-menu-animated dropdown-lg" aria-labelledby="topbar-notifydrop">

                    <!-- item-->
                    <div class="dropdown-item noti-title">
                        <h5 class="m-0">
                                    <span class="float-right">
                                        <a href="javascript: void(0);" class="text-dark">
                                            <small>Clear All</small>
                                        </a>
                                    </span>Notification
                        </h5>
                    </div>

                    <div class="slimscroll" style="max-height: 230px;">
                        <!-- item-->
                        <a href="javascript:void(0);" class="dropdown-item notify-item">
                            <div class="notify-icon bg-primary">
                                <i class="mdi mdi-comment-account-outline"></i>
                            </div>
                            <p class="notify-details">Caleb Flakelar commented on Admin
                                <small class="text-muted">1 min ago</small>
                            </p>
                        </a>

                        <!-- item-->
                        <a href="javascript:void(0);" class="dropdown-item notify-item">
                            <div class="notify-icon bg-info">
                                <i class="mdi mdi-account-plus"></i>
                            </div>
                            <p class="notify-details">New user registered.
                                <small class="text-muted">5 hours ago</small>
                            </p>
                        </a>

                        <!-- item-->
                        <a href="javascript:void(0);" class="dropdown-item notify-item">
                            <div class="notify-icon">
                                <img src="assets/images/users/avatar-2.jpg" class="img-fluid rounded-circle" alt="" /> </div>
                            <p class="notify-details">Cristina Pride</p>
                            <p class="text-muted mb-0 user-msg">
                                <small>Hi, How are you? What about our next meeting</small>
                            </p>
                        </a>

                        <!-- item-->
                        <a href="javascript:void(0);" class="dropdown-item notify-item">
                            <div class="notify-icon bg-primary">
                                <i class="mdi mdi-comment-account-outline"></i>
                            </div>
                            <p class="notify-details">Caleb Flakelar commented on Admin
                                <small class="text-muted">4 days ago</small>
                            </p>
                        </a>

                        <!-- item-->
                        <a href="javascript:void(0);" class="dropdown-item notify-item">
                            <div class="notify-icon">
                                <img src="assets/images/users/avatar-4.jpg" class="img-fluid rounded-circle" alt="" /> </div>
                            <p class="notify-details">Karen Robinson</p>
                            <p class="text-muted mb-0 user-msg">
                                <small>Wow ! this admin looks good and awesome design</small>
                            </p>
                        </a>

                        <!-- item-->
                        <a href="javascript:void(0);" class="dropdown-item notify-item">
                            <div class="notify-icon bg-info">
                                <i class="mdi mdi-heart"></i>
                            </div>
                            <p class="notify-details">Carlos Crouch liked
                                <b>Admin</b>
                                <small class="text-muted">13 days ago</small>
                            </p>
                        </a>
                    </div>

                    <!-- All-->
                    <a href="javascript:void(0);" class="dropdown-item text-center text-primary notify-item notify-all">
                        View All
                    </a>

                </div>
            </li>

            <li class="dropdown notification-list">
                <a class="nav-link right-bar-toggle" href="javascript: void(0);">
                    <i class="dripicons-gear noti-icon"></i>
                </a>
            </li>

            <li class="dropdown notification-list">
                <a class="nav-link dropdown-toggle nav-user arrow-none mr-0" data-toggle="dropdown" id="topbar-userdrop" href="#" role="button" aria-haspopup="true"
                   aria-expanded="false">
                            <span class="account-user-avatar">
                                <img src="assets/images/users/avatar-1.jpg" alt="user-image" class="rounded-circle">
                            </span>
                    <span>
                                <span class="account-user-name">Dominic Keller</span>
                                <span class="account-position">Founder</span>
                            </span>
                </a>
                <div class="dropdown-menu dropdown-menu-right dropdown-menu-animated topbar-dropdown-menu profile-dropdown" aria-labelledby="topbar-userdrop">
                    <!-- item-->
                    <div class=" dropdown-header noti-title">
                        <h6 class="text-overflow m-0">Welcome !</h6>
                    </div>

                    <!-- item-->
                    <a href="javascript:void(0);" class="dropdown-item notify-item">
                        <i class="mdi mdi-account-circle mr-1"></i>
                        <span>My Account</span>
                    </a>

                    <!-- item-->
                    <a href="javascript:void(0);" class="dropdown-item notify-item">
                        <i class="mdi mdi-account-edit mr-1"></i>
                        <span>Settings</span>
                    </a>

                    <!-- item-->
                    <a href="javascript:void(0);" class="dropdown-item notify-item">
                        <i class="mdi mdi-lifebuoy mr-1"></i>
                        <span>Support</span>
                    </a>

                    <!-- item-->
                    <a href="javascript:void(0);" class="dropdown-item notify-item">
                        <i class="mdi mdi-lock-outline mr-1"></i>
                        <span>Lock Screen</span>
                    </a>

                    <!-- item-->
                    <a href="javascript:void(0);" class="dropdown-item notify-item">
                        <i class="mdi mdi-logout mr-1"></i>
                        <span>Logout</span>
                    </a>

                </div>
            </li>

        </ul>
        <a class="button-menu-mobile disable-btn">
            <div class="lines">
                <span></span>
                <span></span>
                <span></span>
            </div>
        </a>
        <div class="app-search">
            <form>
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Search...">
                    <span class="mdi mdi-magnify"></span>
                    <div class="input-group-append">
                        <button class="btn btn-primary" type="submit">Search</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- end Topbar -->


<div class="container-fluid">

    <!-- Begin page -->
    <div class="wrapper">

        <!-- ============================================================== -->
        <!-- Start Page Content here -->
        <!-- ============================================================== -->

        <!-- Start Content-->

        <!-- ========== Left Sidebar Start ========== -->
        <div class="left-side-menu"><%@include file="head.jsp"%></div>
        <!-- Left Sidebar End -->

        <div class="content-page">
            <div class="content">

                <!-- start page title -->
                <div class="row">
                    <div class="col-12">
                        <div class="page-title-box">
                            <div class="page-title-right">
                                <ol class="breadcrumb m-0">
                                    <li class="breadcrumb-item"><a href="javascript: void(0);">Hyper</a></li>
                                    <li class="breadcrumb-item"><a href="javascript: void(0);">eCommerce</a></li>
                                    <li class="breadcrumb-item active">Products</li>
                                </ol>
                            </div>
                            <h4 class="page-title">Products</h4>
                        </div> <!-- end page-title-box -->
                    </div> <!-- end col-->
                </div>
                <!-- end page title -->

                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body">
                                <div class="row mb-2">
                                    <div class="col-sm-4">
                                        <a href="javascript:void(0);" class="btn btn-danger mb-2"><i class="mdi mdi-plus-circle mr-2"></i> Add Products</a>
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="text-sm-right">
                                            <button type="button" class="btn btn-success mb-2 mr-1"><i class="mdi mdi-settings"></i></button>
                                            <button type="button" class="btn btn-light mb-2 mr-1">Import</button>
                                            <button type="button" class="btn btn-light mb-2">Export</button>
                                        </div>
                                    </div><!-- end col-->
                                </div>

                                <div class="table-responsive">
                                    <table class="table table-centered w-100 dt-responsive nowrap" id="products-datatable">
                                        <thead class="thead-light">
                                        <tr>
                                            <th class="all" style="width: 20px;">
                                                <div class="custom-control custom-checkbox">
                                                    <input type="checkbox" class="custom-control-input" id="customCheck1">
                                                    <label class="custom-control-label" for="customCheck1">&nbsp;</label>
                                                </div>
                                            </th>
                                            <th class="all">Product</th>
                                            <th>Category</th>
                                            <th>Added Date</th>
                                            <th>Price</th>
                                            <th>Quantity</th>
                                            <th>Status</th>
                                            <th style="width: 85px;">Action</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>
                                                <div class="custom-control custom-checkbox">
                                                    <input type="checkbox" class="custom-control-input" id="customCheck2">
                                                    <label class="custom-control-label" for="customCheck2">&nbsp;</label>
                                                </div>
                                            </td>
                                            <td>
                                                <img src="assets/images/products/product-1.jpg" alt="contact-img" title="contact-img" class="rounded mr-3" height="48" />
                                                <p class="m-0 d-inline-block align-middle font-16">
                                                    <a href="apps-ecommerce-products-details.html" class="text-body">Amazing Modern Chair</a>
                                                    <br/>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                </p>
                                            </td>
                                            <td>
                                                Aeron Chairs
                                            </td>
                                            <td>
                                                09/12/2018
                                            </td>
                                            <td>
                                                $148.66
                                            </td>

                                            <td>
                                                254
                                            </td>
                                            <td>
                                                <span class="badge badge-success">Active</span>
                                            </td>

                                            <td class="table-action">
                                                <a href="javascript:void(0);" class="action-icon"> <i class="mdi mdi-eye"></i></a>
                                                <a href="javascript:void(0);" class="action-icon"> <i class="mdi mdi-square-edit-outline"></i></a>
                                                <a href="javascript:void(0);" class="action-icon"> <i class="mdi mdi-delete"></i></a>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td>
                                                <div class="custom-control custom-checkbox">
                                                    <input type="checkbox" class="custom-control-input" id="customCheck3">
                                                    <label class="custom-control-label" for="customCheck3">&nbsp;</label>
                                                </div>
                                            </td>
                                            <td>
                                                <img src="assets/images/products/product-4.jpg" alt="contact-img" title="contact-img" class="rounded mr-3" height="48" />
                                                <p class="m-0 d-inline-block align-middle font-16">
                                                    <a href="apps-ecommerce-products-details.html" class="text-body">Biblio Plastic Armchair</a>
                                                    <br/>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star-half"></span>
                                                </p>
                                            </td>
                                            <td>
                                                Wooden Chairs
                                            </td>
                                            <td>
                                                09/08/2018
                                            </td>
                                            <td>
                                                $8.99
                                            </td>

                                            <td>
                                                1,874
                                            </td>
                                            <td>
                                                <span class="badge badge-success">Active</span>
                                            </td>
                                            <td class="table-action">
                                                <a href="javascript:void(0);" class="action-icon"> <i class="mdi mdi-eye"></i></a>
                                                <a href="javascript:void(0);" class="action-icon"> <i class="mdi mdi-square-edit-outline"></i></a>
                                                <a href="javascript:void(0);" class="action-icon"> <i class="mdi mdi-delete"></i></a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="custom-control custom-checkbox">
                                                    <input type="checkbox" class="custom-control-input" id="customCheck4">
                                                    <label class="custom-control-label" for="customCheck4">&nbsp;</label>
                                                </div>
                                            </td>
                                            <td>
                                                <img src="assets/images/products/product-3.jpg" alt="contact-img" title="contact-img" class="rounded mr-3" height="48" />
                                                <p class="m-0 d-inline-block align-middle font-16">
                                                    <a href="apps-ecommerce-products-details.html" class="text-body">Branded Wooden Chair</a>
                                                    <br/>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star-outline"></span>
                                                </p>
                                            </td>
                                            <td>
                                                Dining Chairs
                                            </td>
                                            <td>
                                                09/05/2018
                                            </td>
                                            <td>
                                                $68.32
                                            </td>

                                            <td>
                                                2,541
                                            </td>
                                            <td>
                                                <span class="badge badge-success">Active</span>
                                            </td>

                                            <td class="table-action">
                                                <a href="javascript:void(0);" class="action-icon"> <i class="mdi mdi-eye"></i></a>
                                                <a href="javascript:void(0);" class="action-icon"> <i class="mdi mdi-square-edit-outline"></i></a>
                                                <a href="javascript:void(0);" class="action-icon"> <i class="mdi mdi-delete"></i></a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="custom-control custom-checkbox">
                                                    <input type="checkbox" class="custom-control-input" id="customCheck5">
                                                    <label class="custom-control-label" for="customCheck5">&nbsp;</label>
                                                </div>
                                            </td>
                                            <td>
                                                <img src="assets/images/products/product-4.jpg" alt="contact-img" title="contact-img" class="rounded mr-3" height="48" />
                                                <p class="m-0 d-inline-block align-middle font-16">
                                                    <a href="apps-ecommerce-products-details.html" class="text-body">Designer Awesome Chair</a>
                                                    <br/>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star-half"></span>
                                                    <span class="text-warning mdi mdi-star-outline"></span>
                                                </p>
                                            </td>
                                            <td>
                                                Baby Chairs
                                            </td>
                                            <td>
                                                08/23/2018
                                            </td>
                                            <td>
                                                $112.00
                                            </td>

                                            <td>
                                                3,540
                                            </td>
                                            <td>
                                                <span class="badge badge-success">Active</span>
                                            </td>

                                            <td class="table-action">
                                                <a href="javascript:void(0);" class="action-icon"> <i class="mdi mdi-eye"></i></a>
                                                <a href="javascript:void(0);" class="action-icon"> <i class="mdi mdi-square-edit-outline"></i></a>
                                                <a href="javascript:void(0);" class="action-icon"> <i class="mdi mdi-delete"></i></a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="custom-control custom-checkbox">
                                                    <input type="checkbox" class="custom-control-input" id="customCheck6">
                                                    <label class="custom-control-label" for="customCheck6">&nbsp;</label>
                                                </div>
                                            </td>
                                            <td>
                                                <img src="assets/images/products/product-5.jpg" alt="contact-img" title="contact-img" class="rounded mr-3" height="48" />
                                                <p class="m-0 d-inline-block align-middle font-16">
                                                    <a href="apps-ecommerce-products-details.html" class="text-body">Cardan Armchair</a>
                                                    <br/>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                </p>
                                            </td>
                                            <td>
                                                Plastic Armchair
                                            </td>
                                            <td>
                                                08/02/2018
                                            </td>
                                            <td>
                                                $59.69
                                            </td>

                                            <td>
                                                26
                                            </td>
                                            <td>
                                                <span class="badge badge-success">Active</span>
                                            </td>

                                            <td class="table-action">
                                                <a href="javascript:void(0);" class="action-icon"> <i class="mdi mdi-eye"></i></a>
                                                <a href="javascript:void(0);" class="action-icon"> <i class="mdi mdi-square-edit-outline"></i></a>
                                                <a href="javascript:void(0);" class="action-icon"> <i class="mdi mdi-delete"></i></a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="custom-control custom-checkbox">
                                                    <input type="checkbox" class="custom-control-input" id="customCheck7">
                                                    <label class="custom-control-label" for="customCheck7">&nbsp;</label>
                                                </div>
                                            </td>
                                            <td>
                                                <img src="assets/images/products/product-3.jpg" alt="contact-img" title="contact-img" class="rounded mr-3" height="48" />
                                                <p class="m-0 d-inline-block align-middle font-16">
                                                    <a href="apps-ecommerce-products-details.html" class="text-body">Bootecos Plastic Armchair</a>
                                                    <br/>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star-half"></span>
                                                </p>
                                            </td>
                                            <td>
                                                Wing Chairs
                                            </td>
                                            <td>
                                                07/15/2018
                                            </td>
                                            <td>
                                                $148.66
                                            </td>

                                            <td>
                                                485
                                            </td>
                                            <td>
                                                <span class="badge badge-danger">Deactive</span>
                                            </td>

                                            <td class="table-action">
                                                <a href="javascript:void(0);" class="action-icon"> <i class="mdi mdi-eye"></i></a>
                                                <a href="javascript:void(0);" class="action-icon"> <i class="mdi mdi-square-edit-outline"></i></a>
                                                <a href="javascript:void(0);" class="action-icon"> <i class="mdi mdi-delete"></i></a>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td>
                                                <div class="custom-control custom-checkbox">
                                                    <input type="checkbox" class="custom-control-input" id="customCheck8">
                                                    <label class="custom-control-label" for="customCheck8">&nbsp;</label>
                                                </div>
                                            </td>
                                            <td>
                                                <img src="assets/images/products/product-6.jpg" alt="contact-img" title="contact-img" class="rounded mr-3" height="48" />
                                                <p class="m-0 d-inline-block align-middle font-16">
                                                    <a href="apps-ecommerce-products-details.html" class="text-body">Adirondack Chair</a>
                                                    <br/>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                </p>
                                            </td>
                                            <td>
                                                Aeron Chairs
                                            </td>
                                            <td>
                                                07/07/2018
                                            </td>
                                            <td>
                                                $65.94
                                            </td>

                                            <td>
                                                652
                                            </td>
                                            <td>
                                                <span class="badge badge-success">Active</span>
                                            </td>

                                            <td class="table-action">
                                                <a href="javascript:void(0);" class="action-icon"> <i class="mdi mdi-eye"></i></a>
                                                <a href="javascript:void(0);" class="action-icon"> <i class="mdi mdi-square-edit-outline"></i></a>
                                                <a href="javascript:void(0);" class="action-icon"> <i class="mdi mdi-delete"></i></a>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td>
                                                <div class="custom-control custom-checkbox">
                                                    <input type="checkbox" class="custom-control-input" id="customCheck9">
                                                    <label class="custom-control-label" for="customCheck9">&nbsp;</label>
                                                </div>
                                            </td>
                                            <td>
                                                <img src="assets/images/products/product-2.jpg" alt="contact-img" title="contact-img" class="rounded mr-3" height="48" />
                                                <p class="m-0 d-inline-block align-middle font-16">
                                                    <a href="apps-ecommerce-products-details.html" class="text-body">Bean Bag Chair</a>
                                                    <br/>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                </p>
                                            </td>
                                            <td>
                                                Wooden Chairs
                                            </td>
                                            <td>
                                                06/30/2018
                                            </td>
                                            <td>
                                                $99
                                            </td>

                                            <td>
                                                1,021
                                            </td>
                                            <td>
                                                <span class="badge badge-danger">Deactive</span>
                                            </td>
                                            <td class="table-action">
                                                <a href="javascript:void(0);" class="action-icon"> <i class="mdi mdi-eye"></i></a>
                                                <a href="javascript:void(0);" class="action-icon"> <i class="mdi mdi-square-edit-outline"></i></a>
                                                <a href="javascript:void(0);" class="action-icon"> <i class="mdi mdi-delete"></i></a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="custom-control custom-checkbox">
                                                    <input type="checkbox" class="custom-control-input" id="customCheck10">
                                                    <label class="custom-control-label" for="customCheck10">&nbsp;</label>
                                                </div>
                                            </td>
                                            <td>
                                                <img src="assets/images/products/product-3.jpg" alt="contact-img" title="contact-img" class="rounded mr-3" height="48" />
                                                <p class="m-0 d-inline-block align-middle font-16">
                                                    <a href="apps-ecommerce-products-details.html" class="text-body">The butterfly chair</a>
                                                    <br/>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star-half"></span>
                                                </p>
                                            </td>
                                            <td>
                                                Dining Chairs
                                            </td>
                                            <td>
                                                06/19/2018
                                            </td>
                                            <td>
                                                $58
                                            </td>

                                            <td>
                                                874
                                            </td>
                                            <td>
                                                <span class="badge badge-success">Active</span>
                                            </td>

                                            <td class="table-action">
                                                <a href="javascript:void(0);" class="action-icon"> <i class="mdi mdi-eye"></i></a>
                                                <a href="javascript:void(0);" class="action-icon"> <i class="mdi mdi-square-edit-outline"></i></a>
                                                <a href="javascript:void(0);" class="action-icon"> <i class="mdi mdi-delete"></i></a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="custom-control custom-checkbox">
                                                    <input type="checkbox" class="custom-control-input" id="customCheck11">
                                                    <label class="custom-control-label" for="customCheck11">&nbsp;</label>
                                                </div>
                                            </td>
                                            <td>
                                                <img src="assets/images/products/product-4.jpg" alt="contact-img" title="contact-img" class="rounded mr-3" height="48" />
                                                <p class="m-0 d-inline-block align-middle font-16">
                                                    <a href="apps-ecommerce-products-details.html" class="text-body">Eames Lounge Chair</a>
                                                    <br/>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star-half"></span>
                                                </p>
                                            </td>
                                            <td>
                                                Baby Chairs
                                            </td>
                                            <td>
                                                05/06/2018
                                            </td>
                                            <td>
                                                $39.5
                                            </td>

                                            <td>
                                                1,254
                                            </td>
                                            <td>
                                                <span class="badge badge-success">Active</span>
                                            </td>

                                            <td class="table-action">
                                                <a href="javascript:void(0);" class="action-icon"> <i class="mdi mdi-eye"></i></a>
                                                <a href="javascript:void(0);" class="action-icon"> <i class="mdi mdi-square-edit-outline"></i></a>
                                                <a href="javascript:void(0);" class="action-icon"> <i class="mdi mdi-delete"></i></a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="custom-control custom-checkbox">
                                                    <input type="checkbox" class="custom-control-input" id="customCheck12">
                                                    <label class="custom-control-label" for="customCheck12">&nbsp;</label>
                                                </div>
                                            </td>
                                            <td>
                                                <img src="assets/images/products/product-5.jpg" alt="contact-img" title="contact-img" class="rounded mr-3" height="48" />
                                                <p class="m-0 d-inline-block align-middle font-16">
                                                    <a href="apps-ecommerce-products-details.html" class="text-body">Farthingale Chair</a>
                                                    <br/>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star-half"></span>
                                                </p>
                                            </td>
                                            <td>
                                                Plastic Armchair
                                            </td>
                                            <td>
                                                04/09/2018
                                            </td>
                                            <td>
                                                $78.66
                                            </td>

                                            <td>
                                                524
                                            </td>
                                            <td>
                                                <span class="badge badge-danger">Deactive</span>
                                            </td>

                                            <td class="table-action">
                                                <a href="javascript:void(0);" class="action-icon"> <i class="mdi mdi-eye"></i></a>
                                                <a href="javascript:void(0);" class="action-icon"> <i class="mdi mdi-square-edit-outline"></i></a>
                                                <a href="javascript:void(0);" class="action-icon"> <i class="mdi mdi-delete"></i></a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="custom-control custom-checkbox">
                                                    <input type="checkbox" class="custom-control-input" id="customCheck13">
                                                    <label class="custom-control-label" for="customCheck13">&nbsp;</label>
                                                </div>
                                            </td>
                                            <td>
                                                <img src="assets/images/products/product-6.jpg" alt="contact-img" title="contact-img" class="rounded mr-3" height="48" />
                                                <p class="m-0 d-inline-block align-middle font-16">
                                                    <a href="apps-ecommerce-products-details.html" class="text-body">Unpowered aircraft</a>
                                                    <br/>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star"></span>
                                                    <span class="text-warning mdi mdi-star-half"></span>
                                                </p>
                                            </td>
                                            <td>
                                                Wing Chairs
                                            </td>
                                            <td>
                                                03/24/2018
                                            </td>
                                            <td>
                                                $49
                                            </td>

                                            <td>
                                                204
                                            </td>
                                            <td>
                                                <span class="badge badge-danger">Deactive</span>
                                            </td>

                                            <td class="table-action">
                                                <a href="javascript:void(0);" class="action-icon"> <i class="mdi mdi-eye"></i></a>
                                                <a href="javascript:void(0);" class="action-icon"> <i class="mdi mdi-square-edit-outline"></i></a>
                                                <a href="javascript:void(0);" class="action-icon"> <i class="mdi mdi-delete"></i></a>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div> <!-- end card-body-->
                        </div> <!-- end card-->
                    </div> <!-- end col -->
                </div>
                <!-- end row -->

            </div> <!-- content -->

            <!-- Footer Start -->
            <footer class="footer">
                <div class="row">
                    <div class="col-md-6">
                        2018 - 2019 © Hyper - Coderthemes.com
                    </div>
                    <div class="col-md-6">
                        <div class="text-md-right footer-links d-none d-md-block">
                            <a href="javascript: void(0);">About</a>
                            <a href="javascript: void(0);">Support</a>
                            <a href="javascript: void(0);">Contact Us</a>
                        </div>
                    </div>
                </div>
            </footer>
            <!-- end Footer -->
        </div> <!-- content-page -->

    </div> <!-- end wrapper-->

    <!-- ============================================================== -->
    <!-- End Page content -->
    <!-- ============================================================== -->


</div>
<!-- END Container -->


<!-- Right Sidebar -->
<div class="right-bar"><%@include file="right-bar.jsp"%></div>


<div class="rightbar-overlay"></div>
<!-- /Right-bar -->


<!-- App js -->
<script src="assets/js/app.min.js"></script>

<!-- third party js -->
<script src="assets/js/vendor/jquery.dataTables.min.js"></script>
<script src="assets/js/vendor/dataTables.bootstrap4.js"></script>
<script src="assets/js/vendor/dataTables.responsive.min.js"></script>
<script src="assets/js/vendor/responsive.bootstrap4.min.js"></script>
<script src="assets/js/vendor/dataTables.checkboxes.min.js"></script>
<!-- third party js ends -->

<!-- demo app -->
<script src="assets/js/pages/demo.products.js"></script>
<!-- end demo js-->

</body>

</html>
