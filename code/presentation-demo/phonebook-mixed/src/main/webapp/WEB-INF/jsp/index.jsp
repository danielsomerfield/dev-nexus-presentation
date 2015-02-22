<html id="phonebook-app" ng-app="phonebookApp">
<head>
    <link rel="stylesheet" href="../../static/js/lib/bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" href="../../static/js/lib/bootstrap/css/bootstrap-theme.css"/>
    <script src="../../static/js/lib/jquery-2.1.3.js"></script>
    <script src="../../static/js/lib/angular.min.js"></script>
    <script src="../../static/js/app.js"></script>
    <script src="../../static/js/controllers.js"></script>
</head>

<body ng-controller="PhonebookController">
<header>
    <div class="navbar">
        <div class="navbar-inner">
            <div class="container-fluid">
                <div class="navbar-brand">Phone Book</div>
                <form class="navbar-form navbar-right" id="login-form">
                    <input type="text" class="form-control" placeholder="Username" />
                    <input type="password" class="form-control" placeholder="Password" />
                </form>
            </div>
        </div>
    </div>
</header>
<main>
    <div class="container-fluid">
        <form class="">
            <input type="text" class="form-control" placeholder="Filter" />
        </form>
        <table class="table">
            <thead>
            <tr>
                <td>Last Name</td>
                <td>First Name</td>
                <td>Email</td>
            </tr>
            </thead>
            <tbody>
                <tr class="phonebook-entry" ng-repeat="entry in phonebookEntries" data-id="{{entry.id}}">

                </tr>
            </tbody>
        </table>
    </div>
</main>
</body>
</html>