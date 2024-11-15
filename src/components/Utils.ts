export const convertBase64ToImageUrl = (base64: string): string => {
    const byteCharacters = atob(base64);
    const byteNumbers = new Uint8Array(byteCharacters.length);
  
    for (let i = 0; i < byteCharacters.length; i++) {
      byteNumbers[i] = byteCharacters.charCodeAt(i);
    }
  
    const blob = new Blob([byteNumbers], { type: 'image/jpeg' });
    return URL.createObjectURL(blob);
  };