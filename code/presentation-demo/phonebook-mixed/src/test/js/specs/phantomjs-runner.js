(function () {
    var PhantomJasmineRunner, url, page, runner;

    PhantomJasmineRunner = (function () {
        function PhantomJasmineRunner(page, exit_func) {
            this.page = page;
            this.exit_func = exit_func != null ? exit_func : phantom.exit;
        }

        PhantomJasmineRunner.prototype.get_status = function () {
            return this.page.evaluate(function () {
                return consoleReporter.status;
            });
        };

        return PhantomJasmineRunner;
    })();

    if (phantom.args.length === 0) {
        console.error("Need a url as the argument");
        phantom.exit(1);
    }

    var exitCode = 0;

    page = new WebPage();

    runner = new PhantomJasmineRunner(page);

    page.onError = function (msg) {
        console.error(msg);
        return runner.exit_func(2);
    };

    page.onConsoleMessage = function (msg) {
        var matcher = /\d\sspec[s]?,\s(\d)\sfailure[s]?/;
        var groups = msg.match(matcher);
        if (groups != null && groups.length == 2) {
            exitCode = parseInt(groups[1]) > 0 ? 1 : 0;
        }
        if (/Finished in [^\s]+ second/.test(msg)) {
            phantom.exit(exitCode);
        }

        return console.log(msg);
    };

    url = phantom.args[0];

    page.open(url, function (status) {
        if (status !== "success") {
            return phantom.exit(1);
        }
    });

}).call(this);
