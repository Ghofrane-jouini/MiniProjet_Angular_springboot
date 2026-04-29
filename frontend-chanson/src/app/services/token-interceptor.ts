import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { Auth } from './auth';

const exclude_array: string[] = ['/users/login', '/users/register', '/users/verifyEmail'];

function toExclude(url: string): boolean {
  return exclude_array.some(path => url.includes(path));
}

export const tokenInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(Auth);

  if (toExclude(req.url)) {
    return next(req);
  }

  if (!authService.getToken()) {
    authService.loadToken();
  }

  const jwt = authService.getToken();
  if (jwt) {
    const reqWithToken = req.clone({
      setHeaders: { Authorization: 'Bearer ' + jwt },
    });
    return next(reqWithToken);
  }

  return next(req);
};