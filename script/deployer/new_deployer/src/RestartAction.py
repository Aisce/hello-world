
#coding=utf8

'''
Created on May 1, 2016

@author: renshui
'''
from ActionBase import ActionBase

class RestartAction(ActionBase):
    ''' 重启 '''
    def __init__(self, all_config=None, app_config=None, host_config=None, tag=None, operations=None, app_name=None, ip=None):
        super(RestartAction, self).__init__(all_config=all_config, app_config=app_config, host_config=host_config, tag=tag, app_name=app_name, ip=ip)
        self.operations = operations
        
    def do_execute(self):
        self.operations.login()
        self.operations.stop_app()
        self.operations.start_app()
        
    def cleanup(self):
        self.operations.cleanup()
        