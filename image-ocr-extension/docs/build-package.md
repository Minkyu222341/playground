# Chrome 웹 스토어 배포 가이드

## 📁 프로젝트 구조
```
image-ocr-extension/
├── src/                    # 소스 코드
│   ├── manifest.json
│   ├── popup.html
│   ├── popup.js
│   └── icons/
├── dist/                   # 빌드 결과물 (배포용)
├── assets/                 # 스토어 이미지들
├── tools/                  # 개발 도구들
├── docs/                   # 문서들
└── build.ps1              # 빌드 스크립트
```

## 🚀 빌드 및 배포 과정

### 1. 빌드 실행
```powershell
.\build.ps1
```

### 2. 생성되는 파일들
- `dist/` 폴더에 배포용 파일들 복사
- `image-ocr-extension.zip` 생성

### 3. Chrome 웹 스토어 업로드
1. Chrome 개발자 대시보드 접속
2. `image-ocr-extension.zip` 업로드
3. 스토어 정보 입력 (`docs/store-description.md` 참고)
4. 심사 제출

## 📋 포함되는 파일들 (필수)
- manifest.json
- popup.html  
- popup.js
- icon16.png, icon48.png, icon128.png