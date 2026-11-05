# Lab C — Docker Hub 이미지 실행 + GitHub Actions 자동화

보완 교안(`massai-hub-ci-guide.zip` 안의 `hub-and-ci-guide.md`)을 따라 진행하세요.

- **Part 1 (Docker Hub)**: 이미지를 `docker build` → `docker push` → 다른 환경에서 `docker compose pull`로 받아 실행
- **Part 2 (GitHub Actions)**: 저장소에 push하면 `.github/workflows/ci.yml`이 테스트하고, 통과하면 이미지를 자동으로 Docker Hub에 올립니다

## 시작 전 설정 두 가지

1. `.env`의 `DOCKERHUB_USER`를 본인 Docker Hub 아이디로 바꿉니다.
2. 자바 코드, Dockerfile, 화면은 Lab B와 같습니다. 이번에 추가된 것은 다음 두 가지입니다.
   - 화면 하단의 **App version** (이미지에 구워 넣은 버전)
   - `.github/workflows/ci.yml` (자동화 설정)

## 빠른 확인

```bash
docker compose config > /dev/null && echo "compose 파일 OK"   # 변수가 채워진 설정이 출력되면 성공
./mvnw -B verify                                               # 로컬에서 테스트까지 한 번에
```

> 보안 주의: Docker Hub 비밀번호나 토큰을 이 저장소의 파일에 적지 마세요. 토큰은 GitHub의 Secrets에만 넣습니다.
