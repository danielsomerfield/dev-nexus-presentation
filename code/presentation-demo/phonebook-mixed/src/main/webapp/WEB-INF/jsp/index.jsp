<html id="phonebook-app">
<head>
    <link rel="stylesheet" href="../../static/js/lib/bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" href="../../static/js/lib/bootstrap/css/bootstrap-theme.css"/>
</head>

<body>
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
        </table>
    </div>
</main>
</body>
</html>