var appModule = angular.module('MyApp', []);

appModule.value('MyList', [{
    name: 'test1',
    values: ['a', 'b']},
{
    name: 'test2',
    values: ['c', 'd']
}]);

function CtrlA(MyList) {
    this.list = MyList;
    this.selection = MyList[0];
}