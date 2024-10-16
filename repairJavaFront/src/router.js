import { createRouter, createWebHashHistory} from 'vue-router';

const routes = [
  {
    path: '/',
    component: () => import('@/pages/problems')
  },
  {
    path: '/problems',
    component: () => import('@/pages/problems')
  },
  {
    path: '/upload/:id',
    name: 'upload',
    component: () => import('@/pages/upload')
  },
  {
    path: '/refactor',
    component: () => import('@/pages/refactor')
  },
  {
    path: '/repair',
    component: () => import('@/pages/repair')
  },
  {
    path: '/repairDetails/:id',
    name: 'repairDetails',
    component: () => import('@/pages/repairDetails'),
    children:[
      {
        path: '',
        component: () => import('@/components/RepairBlockMatchTable')
      },
      {
        path: 'blockMatch',
        component: () => import('@/components/RepairBlockMatchTable')
      },
      {
        path: 'variableMatch',
        component: () => import('@/components/RepairVariableMatchTable')
      },
      {
        path: 'faultLocate',
        component: () => import('@/components/RepairFaultLocateTreeview')
      },
      {
        path: 'statementMatch',
        component: () => import('@/components/RepairStatementMatchTable')
      },
      {
        path: 'codeResult',
        component: () => import('@/components/RepairCodeResult')
      }
    ]
  },
]

const router=createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
